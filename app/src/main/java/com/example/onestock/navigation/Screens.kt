package com.example.onestock.navigation

sealed class Screens(val route: String) {
    object Home: Screens(route = "homeScreen")
}