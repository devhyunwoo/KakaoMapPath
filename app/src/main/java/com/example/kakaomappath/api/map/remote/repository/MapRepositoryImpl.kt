package com.example.kakaomappath.api.map.remote.repository

import com.example.kakaomappath.api.map.MapApiService
import com.example.kakaomappath.api.map.remote.model.GetCodingAssignmentDistanceTime
import com.example.kakaomappath.api.map.remote.model.GetCodingAssignmentLocationsResponse
import com.example.kakaomappath.api.map.remote.model.GetCodingAssignmentRoutes
import retrofit2.Response
import javax.inject.Inject

class MapRepositoryImpl @Inject constructor(
    private val mapApiService: MapApiService
) : MapRepository {
    override suspend fun getCodingAssignmentLocations(): Response<GetCodingAssignmentLocationsResponse> {
        return mapApiService.getCodingAssignmentLocations()
    }

    override suspend fun getCodingAssignmentRoutes(
        origin: String,
        destination: String
    ): Response<List<GetCodingAssignmentRoutes>> {
        return mapApiService.getCodingAssignmentRoutes(
            origin = origin,
            destination = destination
        )
    }

    override suspend fun getCodingAssignmentDistanceTime(
        origin: String,
        destination: String
    ): Response<GetCodingAssignmentDistanceTime> {
        return mapApiService.getCodingAssignmentDistanceTime(
            origin = origin,
            destination = destination
        )
    }

}