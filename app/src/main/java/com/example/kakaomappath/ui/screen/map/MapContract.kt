package com.example.kakaomappath.ui.screen.map

import com.example.kakaomappath.api.common.ApiResult
import com.example.kakaomappath.api.map.remote.model.GetCodingAssignmentRoutes.TrafficState
import com.kakao.vectormap.LatLng

sealed class MapContract {
    data class State(
        // 로딩 중이면 true, 아니면 false
        val isLoading: Boolean,

        // 시간 (몇분 몇초로 맵핑)
        val time: String,

        // 거리 (몇km로 맵핑)
        val distance: String,

        // 경로 조회 시 에러
        val getRoutesErrorData: ApiResult.Error?,

        // 거리/시간 조회 시 에러
        val getDistanceTimeErrorData: ApiResult.Error?
    )

    sealed class Event {
        // 데이터 불러오기
        data class FetchData(
            val origin: String,
            val destination: String
        ) : Event()
    }

    sealed class Effect {
        // 경로 그리기
        data class DrawRoutes(
            val routes: List<Pair<List<LatLng>, TrafficState>>,
        ) : Effect()
    }
}