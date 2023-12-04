package com.example.onestock.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.onestock.screens.HomeScreen


@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screens.Home.route) {
        composable(route = Screens.Home.route) {
            HomeScreen(navController)
        }
    }
}