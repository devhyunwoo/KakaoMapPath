package com.example.kakaomappath.ui.navigation.route

sealed class Route(val route : String) {
    data object Main : Route(route = "main")
}