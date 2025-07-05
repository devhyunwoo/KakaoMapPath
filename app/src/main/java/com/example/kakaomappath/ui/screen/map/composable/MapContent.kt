package com.example.kakaomappath.ui.screen.map.composable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.MapLifeCycleCallback
import com.kakao.vectormap.MapView

@Composable
fun MapContent(
    modifier: Modifier = Modifier,
    mapView: MapView,
    onMapDestroy: () -> Unit,
    onMapError: (Exception?) -> Unit,
    onMapReady: (KakaoMap) -> Unit
) {
    AndroidView(
        modifier = modifier.fillMaxSize(),
        factory = { context ->
            mapView.apply {
                start(
                    object : MapLifeCycleCallback() {
                        override fun onMapDestroy() {
                            onMapDestroy()
                        }

                        override fun onMapError(p0: Exception?) {
                            onMapError(p0)
                        }
                    },
                    object : KakaoMapReadyCallback() {
                        override fun onMapReady(p0: KakaoMap) {
                            onMapReady(p0)
                        }
                    }
                )
            }
        }
    )
}