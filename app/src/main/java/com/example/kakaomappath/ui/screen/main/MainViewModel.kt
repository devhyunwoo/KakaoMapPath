package com.example.kakaomappath.ui.screen.main

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.kakaomappath.api.common.ApiResult
import com.example.kakaomappath.api.common.safeApiCall
import com.example.kakaomappath.api.map.remote.repository.MapRepository
import com.example.kakaomappath.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mapRepository: MapRepository
) : BaseViewModel<MainContract.State, MainContract.Event>(
    initialState = MainContract.State(
        isLoading = false,
        locations = emptyList(),
        errorData = null
    )
) {
    private val _effect: MutableSharedFlow<MainContract.Effect> = MutableSharedFlow()
    val effect: SharedFlow<MainContract.Effect> = _effect.asSharedFlow()

    init {
        getCodingAssignmentLocations()
    }

    private fun getCodingAssignmentLocations() {
        updateState { copy(isLoading = true) }
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
            Log.e(TAG, "getCodingAssignmentLocations: throwable: ${throwable.message}")
            updateState { copy(isLoading = false, errorData = ApiResult.Error(errorMsg = throwable.message)) }
        }) {
            when (val result = safeApiCall { mapRepository.getCodingAssignmentLocations() }) {
                is ApiResult.Success -> {
                    updateState { copy(locations = result.data.locations, isLoading = false) }
                }

                is ApiResult.Error -> {
                    updateState { copy(errorData = result, isLoading = false) }
                }
            }
        }
    }

    override fun requestAction(event: MainContract.Event) {
        when (event) {
            is MainContract.Event.OnClickItem -> {
                viewModelScope.launch {
                    _effect.emit(
                        MainContract.Effect.NavigateToMap(
                            origin = event.origin,
                            destination = event.destination
                        )
                    )
                }
            }
        }
    }


}