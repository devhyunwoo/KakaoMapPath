package com.example.kakaomappath.ui.screen

import com.example.kakaomappath.api.common.ApiResult
import com.example.kakaomappath.api.map.remote.model.GetCodingAssignmentLocationsResponse
import com.example.kakaomappath.api.map.remote.model.Location

sealed class MainContract {

    data class State(
        // 데이터 통신 중이면 true, 아니면 false
        val isLoading : Boolean,
        // 성공 시
        val locations : List<Location>,

        // 에러 발생시
        val errorData : ApiResult.Error<GetCodingAssignmentLocationsResponse>?
    )

    sealed class Event {

    }

    sealed class Effect {

    }
}