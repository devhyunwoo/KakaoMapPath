package com.example.kakaomappath.ui.navigation.route

import kotlinx.serialization.Serializable

sealed interface Route {
    // 메인 화면 (출발지 도착지 목록)
    @Serializable
    data object Main : Route

    // 지도 화면 (메인에서 선택하면 넘어오는 화면)
    @Serializable
    data class Map(
        val origin: String,
        val destination: String
    ) : Route
}