package com.example.kakaomappath.ui.screen

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.kakaomappath.api.common.ApiResult
import com.example.kakaomappath.api.common.safeApiCall
import com.example.kakaomappath.api.map.remote.repository.MapRepository
import com.example.kakaomappath.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mapRepository: MapRepository
) : BaseViewModel<MainContract.State>(
    initialState = MainContract.State(
        isLoading = false,
        locations = emptyList(),
        errorData = null
    )
) {
    init {
        getCodingAssignmentLocations()
    }

    private fun getCodingAssignmentLocations() {
        updateState { copy(isLoading = true) }
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
            Log.d(TAG, "getCodingAssignmentLocations: throwable: ${throwable.message}")
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
}