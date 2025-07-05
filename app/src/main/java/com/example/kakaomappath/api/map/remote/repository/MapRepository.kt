package com.example.kakaomappath.api.map.remote.repository

import com.example.kakaomappath.api.map.remote.model.GetCodingAssignmentDistanceTime
import com.example.kakaomappath.api.map.remote.model.GetCodingAssignmentLocationsResponse
import com.example.kakaomappath.api.map.remote.model.GetCodingAssignmentRoutes
import retrofit2.Response

interface MapRepository {
    // 출발지 도착지 가져오기
    suspend fun getCodingAssignmentLocations(): Response<GetCodingAssignmentLocationsResponse>

    // 경로 조회
    suspend fun getCodingAssignmentRoutes(
        origin: String,
        destination: String
    ): Response<List<GetCodingAssignmentRoutes>>

    // 시간 / 거리 조회
    suspend fun getCodingAssignmentDistanceTime(
        origin: String,
        destination: String
    ): Response<GetCodingAssignmentDistanceTime>
}