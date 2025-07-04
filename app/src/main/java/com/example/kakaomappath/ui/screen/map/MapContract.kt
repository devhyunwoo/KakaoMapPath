package com.example.kakaomappath.ui.screen.map

sealed class MapContract {
    data class State(
        val isLoading: Boolean
    )

    sealed class Event

    sealed class Effect
}