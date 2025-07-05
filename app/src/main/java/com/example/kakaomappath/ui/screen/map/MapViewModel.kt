package com.example.kakaomappath.ui.screen.map

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.kakaomappath.api.common.ApiResult
import com.example.kakaomappath.api.common.safeApiCall
import com.example.kakaomappath.api.map.remote.repository.MapRepository
import com.example.kakaomappath.base.BaseViewModel
import com.kakao.vectormap.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val mapRepository: MapRepository
) : BaseViewModel<MapContract.State, MapContract.Event>(
    initialState = MapContract.State(
        isLoading = false,
        routes = emptyList(),
        time = "",
        distance = "",
        getRoutesErrorData = ApiResult.Error(),
        getDistanceTimeErrorData = ApiResult.Error(),
    )
) {
    private val _effect: MutableSharedFlow<MapContract.Effect> = MutableSharedFlow()
    val effect: SharedFlow<MapContract.Effect> = _effect.asSharedFlow()

    suspend fun getCodingAssignmentRoutes(origin: String, destination: String) {
        when (val result = safeApiCall {
            mapRepository.getCodingAssignmentRoutes(
                origin = origin,
                destination = destination
            )
        }) {
            is ApiResult.Success -> {
                updateState { copy(routes = result.data.map { it.points.toMapLatLng() to it.trafficState }) }
            }

            is ApiResult.Error -> {
                updateState { copy(getRoutesErrorData = result) }
            }
        }
    }

    suspend fun getCodingAssignmentDistanceTime(origin: String, destination: String) {
        when (val result = safeApiCall {
            mapRepository.getCodingAssignmentDistanceTime(
                origin = origin,
                destination = destination
            )
        }) {
            is ApiResult.Success -> {
                updateState {
                    copy(
                        distance = result.data.distance.toFormattedDistance(),
                        time = result.data.time.toFormattedTime()
                    )
                }
            }

            is ApiResult.Error -> {
                updateState { copy(getDistanceTimeErrorData = result) }
            }
        }
    }


    override fun requestAction(event: MapContract.Event) {
        when (event) {
            is MapContract.Event.FetchData -> {
                viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
                    updateState { copy(isLoading = false) }
                    Log.e(TAG, "getCodingAssignmentLocations: throwable: ${throwable.message}")
                }) {
                    updateState { copy(isLoading = true) }
                    val getRoutesDeferred = async {
                        getCodingAssignmentRoutes(
                            origin = event.origin,
                            destination = event.destination
                        )
                    }
                    val getDistanceTimeDeferred = async {
                        getCodingAssignmentDistanceTime(
                            origin = event.origin,
                            destination = event.destination
                        )
                    }

                    listOf(
                        getRoutesDeferred,
                        getDistanceTimeDeferred
                    ).awaitAll()

                    updateState { copy(isLoading = false) }
                }
            }
        }
    }


    fun Int.toFormattedTime(): String {
        return when {
            this < 60 -> "${this}초"
            this < 3600 -> "${this / 60}분"
            else -> {
                val hours = this / 3600
                val minutes = (this % 3600) / 60
                if (minutes == 0) "${hours}시간"
                else "${hours}시간 ${minutes}분"
            }
        }
    }


    fun Int.toFormattedDistance(): String {
        return if (this >= 1000) {
            String.format("%.1f km", this / 1000.0)
        } else {
            "$this m"
        }
    }

    fun String.toMapLatLng(): List<LatLng> {
        return this.split(" ")
            .map { latLng ->
                val parts = latLng.split(",")
                LatLng.from(parts[0].toDouble(), parts[1].toDouble())
            }
    }
}