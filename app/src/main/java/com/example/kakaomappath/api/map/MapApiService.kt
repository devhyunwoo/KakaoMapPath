package com.example.kakaomappath.api.map

import com.example.kakaomappath.api.map.remote.model.GetCodingAssignmentDistanceTime
import com.example.kakaomappath.api.map.remote.model.GetCodingAssignmentLocationsResponse
import com.example.kakaomappath.api.map.remote.model.GetCodingAssignmentRoutes
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MapApiService {

    // 출발지/도착지 리스트 API
    @GET("coding-assignment/locations")
    suspend fun getCodingAssignmentLocations(): Response<GetCodingAssignmentLocationsResponse>

    // 경로 조회 API
    @GET("coding-assignment/routes")
    suspend fun getCodingAssignmentRoutes(
        @Query("origin") origin: String,
        @Query("destination") destination: String
    ): Response<List<GetCodingAssignmentRoutes>>

    // 시간 / 거리 조회 API
    @GET("coding-assignment/distance-time")
    suspend fun getCodingAssignmentDistanceTime(
        @Query("origin") origin: String,
        @Query("destination") destination: String
    ): Response<GetCodingAssignmentDistanceTime>
}