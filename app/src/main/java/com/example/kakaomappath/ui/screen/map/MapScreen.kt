package com.example.kakaomappath.ui.screen.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.kakaomappath.AppColors
import com.example.kakaomappath.ui.screen.common.CommonTopBar
import com.example.kakaomappath.ui.screen.map.composable.MapContent
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.MapView

@Composable
fun MapScreen(
    viewModel: MapViewModel = hiltViewModel(),
    origin: String,
    destination: String,
    navigateUp: () -> Unit
) {
    val context = LocalContext.current
    val mapView = remember { MapView(context) }
    val state = viewModel.state.collectAsState()
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
//                    mapView.seg
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
            onMapReady = ::onMapReady
        )
    }
}

fun onMapDestroy() {

}

fun onMapError(exception: Exception?) {

}

fun onMapReady(kakaoMap: KakaoMap) {

}