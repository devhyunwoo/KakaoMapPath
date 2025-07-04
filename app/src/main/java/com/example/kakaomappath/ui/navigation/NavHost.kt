package com.example.kakaomappath.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.kakaomappath.ui.navigation.route.Route
import com.example.kakaomappath.ui.screen.main.MainScreen
import com.example.kakaomappath.ui.screen.map.MapScreen

@Composable
fun MapNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Route.Main
    ) {
        composable<Route.Main> {
            MainScreen(
                navigate = { route ->
                    navController.navigate(route = route)
                }
            )
        }

        composable<Route.Map> {
            val arg = it.toRoute<Route.Map>()
            val origin = arg.origin
            val destination = arg.destination

            MapScreen(
                origin = origin,
                destination = destination,
                navigateUp = {
                    navController.navigateUp()
                }
            )
        }
    }
}