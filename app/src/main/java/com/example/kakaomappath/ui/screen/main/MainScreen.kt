package com.example.kakaomappath.ui.screen.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.kakaomappath.ui.navigation.route.Route
import com.example.kakaomappath.ui.screen.common.CommonTopBar
import com.example.kakaomappath.ui.screen.main.composable.MainContent

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
    navigate: (Route.Map) -> Unit,
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is MainContract.Effect.NavigateToMap -> {
                    navigate(
                        Route.Map(
                            origin = effect.origin,
                            destination = effect.destination
                        )
                    )
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        topBar = {
            CommonTopBar(title = "출발지 / 도착지 목록", isShowBack = false)
        },
        containerColor = Color.White
    ) { paddingValues ->
        MainContent(
            modifier = Modifier.padding(paddingValues),
            locations = state.locations,
        )
    }
}