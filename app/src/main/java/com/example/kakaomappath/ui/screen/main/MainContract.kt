package com.example.kakaomappath.ui.screen.main

import com.example.kakaomappath.api.common.ApiResult
import com.example.kakaomappath.api.map.remote.model.Location

sealed class MainContract {

    data class State(
        // 데이터 통신 중이면 true, 아니면 false
        val isLoading: Boolean,
        // 성공 시
        val locations: List<Location>,

        // 에러 발생시
        val errorData: ApiResult.Error?
    )

    sealed class Event {
        // 아이템 클릭 했을 때
        data class OnClickItem(
            val origin: String,
            val destination: String
        ) : Event()
    }

    sealed class Effect {
        data class NavigateToMap(
            val origin: String,
            val destination: String
        ) : Effect()
    }
}