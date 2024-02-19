package com.example.onestock.navigation

sealed class Screens(val route: String) {
    object Home: Screens(route = "homeScreen")
    object StockDetail : Screens("stockDetail/{symbol}"){
        fun createRoute(symbol: String) = "stockDetail/$symbol"
    }
    object StockNews : Screens("stockNews")

    object Zakat : Screens("zakat")
}