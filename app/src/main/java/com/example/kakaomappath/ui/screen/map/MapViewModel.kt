package com.example.kakaomappath.ui.screen.map

import com.example.kakaomappath.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor() : BaseViewModel<MapContract.State, MapContract.Event>(
    initialState = MapContract.State(isLoading = false)
) {
    override fun requestAction(event: MapContract.Event) {
        when (event) {

            else -> {}
        }
    }
}