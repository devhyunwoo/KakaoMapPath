package com.example.kakaomappath.api.map

import com.example.kakaomappath.api.map.remote.model.GetCodingAssignmentLocationsResponse
import retrofit2.Response
import retrofit2.http.GET

interface MapApiService {

    // 출발지/도착지 리스트 API
    @GET("coding-assignment/locations")
    suspend fun getCodingAssignmentLocations() : Response<GetCodingAssignmentLocationsResponse>
}