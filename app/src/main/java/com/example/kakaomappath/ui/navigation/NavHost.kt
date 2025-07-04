package com.example.kakaomappath.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kakaomappath.ui.navigation.route.Route
import com.example.kakaomappath.ui.screen.MainScreen

@Composable
fun MapNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = Route.Main.route) {
        composable(Route.Main.route) {
            MainScreen()
        }
    }
}