package com.example.kakaomappath.api.map.remote.repository

import com.example.kakaomappath.api.map.remote.model.GetCodingAssignmentLocationsResponse
import retrofit2.Response

interface MapRepository {
    suspend fun getCodingAssignmentLocations(): Response<GetCodingAssignmentLocationsResponse>
}