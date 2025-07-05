package com.example.kakaomappath.api.map.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetCodingAssignmentDistanceTime(
    @SerialName("distance")
    val distance: Int,
    @SerialName("time")
    val time: Int
)
