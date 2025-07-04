package com.example.kakaomappath.api.map.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetCodingAssignmentLocationsResponse(
    @SerialName("locations")
    val locations: List<Location>
)

@Serializable
data class Location(
    @SerialName("origin")
    val origin: String,
    @SerialName("destination")
    val destination: String
)
