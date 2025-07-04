package com.example.kakaomappath.api.map.remote.repository

import com.example.kakaomappath.api.map.MapApiService
import com.example.kakaomappath.api.map.remote.model.GetCodingAssignmentLocationsResponse
import retrofit2.Response
import javax.inject.Inject

class MapRepositoryImpl @Inject constructor(
    private val mapApiService: MapApiService
) : MapRepository {
    override suspend fun getCodingAssignmentLocations(): Response<GetCodingAssignmentLocationsResponse> {
        return mapApiService.getCodingAssignmentLocations()
    }

}