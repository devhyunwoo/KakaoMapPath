package com.example.kakaomappath.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel<US, UE>(
    initialState: US
) : ViewModel() {
    private val _state : MutableStateFlow<US> = MutableStateFlow(initialState)
    val state : StateFlow<US> = _state.asStateFlow()

    protected val TAG: String = this::class.simpleName ?: "BaseViewModel"

    protected fun updateState(reducer: US.() -> US) {
        val copy = reducer(state.value)
        _state.update { copy}
    }

    abstract fun requestAction(event : UE)
}