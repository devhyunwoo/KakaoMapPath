package com.example.kakaomappath.ui.screen.map

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MapScreen(
    viewModel: MapViewModel = hiltViewModel(),
    origin: String,
    destination: String,
    navigateUp: () -> Unit
) {

}