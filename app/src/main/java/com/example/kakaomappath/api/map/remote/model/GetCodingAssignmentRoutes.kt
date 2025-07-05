package com.example.kakaomappath.api.map.remote.model

import androidx.compose.ui.graphics.Color
import com.example.kakaomappath.AppColors
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetCodingAssignmentRoutes(
    @SerialName("points")
    val points: String,
    @SerialName("traffic_state")
    val trafficState: TrafficState
) {
    enum class TrafficState(val color: Color) {
        UNKNOWN(color = AppColors.RouteUnknown),
        JAM(color = AppColors.RouteJam),
        DELAY(color = AppColors.RouteDelay),
        SLOW(color = AppColors.RouteSlow),
        NORMAL(color = AppColors.RouteNormal),
        BLOCK(color = AppColors.Black)
    }
}
