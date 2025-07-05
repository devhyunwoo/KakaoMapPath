package com.example.kakaomappath.ui.screen.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import com.example.kakaomappath.AppColors
import com.example.kakaomappath.ui.screen.common.CommonTopBar
import com.example.kakaomappath.ui.screen.map.composable.MapContent
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.MapView
import com.kakao.vectormap.route.RouteLineOptions
import com.kakao.vectormap.route.RouteLineSegment
import com.kakao.vectormap.route.RouteLineStyle
import com.kakao.vectormap.route.RouteLineStyles


@Composable
fun MapScreen(
    viewModel: MapViewModel = hiltViewModel(),
    origin: String,
    destination: String,
    navigateUp: () -> Unit
) {
    var kakaoMap by remember { mutableStateOf<KakaoMap?>(null) }
    val context = LocalContext.current
    val mapView = remember { MapView(context) }
    val state = viewModel.state.collectAsState()

    LifecycleEventEffect(event = Lifecycle.Event.ON_RESUME) {
        mapView.resume()
    }

    LifecycleEventEffect(event = Lifecycle.Event.ON_PAUSE) {
        mapView.pause()
    }
    LaunchedEffect(key1 = Unit) {
        viewModel.requestAction(
            event = MapContract.Event.FetchData(
                origin = origin,
                destination = destination
            )
        )
    }
    LaunchedEffect(key1 = Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is MapContract.Effect.DrawRoutes -> {
                    kakaoMap?.let { map ->
                        val segments = effect.routes.map {
                            RouteLineSegment.from(
                                it.first,
                                RouteLineStyles.from(
                                    RouteLineStyle.from(15f, it.second.color.toArgb())
                                )
                            )
                        }
                        val option = RouteLineOptions.from(segments)
                        map.routeLineManager?.layer?.addRouteLine(option)?.show()
                    }
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        topBar = {
            CommonTopBar(
                title = "경로 화면",
                isShowBack = true,
                onBackClick = navigateUp
            )
        },
        containerColor = AppColors.White
    ) { paddingValues ->
        MapContent(
            modifier = Modifier.padding(paddingValues),
            mapView = mapView,
            onMapDestroy = ::onMapDestroy,
            onMapError = ::onMapError,
            onMapReady = { map ->
                kakaoMap = map
                onMapReady(kakaoMap = map)
            }
        )
    }
}

fun onMapDestroy() {

}

fun onMapError(exception: Exception?) {

}

fun onMapReady(kakaoMap: KakaoMap) {
    with(kakaoMap) {
//        // 사용자 이벤트 금지
//        setGestureEnable(GestureType.Pan, false)
//        setGestureEnable(GestureType.Zoom, false)
//        setGestureEnable(GestureType.Rotate, false)
//        setGestureEnable(GestureType.Tilt, false)
//        setGestureEnable(GestureType.OneFingerZoom, false)
//        setGestureEnable(GestureType.TwoFingerSingleTap, false)
//        setGestureEnable(GestureType.OneFingerDoubleTap, false)
    }
}