package com.example.onestock.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.onestock.screens.home.HomeScreen
import com.example.onestock.screens.details.StockDetailScreen
import com.example.onestock.screens.news.StockNewsScreen
import com.example.onestock.screens.screener.StockScreenerScreen
import com.example.onestock.screens.zakat.ZakatScreen
import com.example.onestock.viewmodels.InjectorUtils
import com.example.onestock.viewmodels.StockViewModel


@Composable
fun SetupNavGraph(
    navController: NavHostController,
    context: Context
) {
    val stockViewModel: StockViewModel =
        viewModel(factory = InjectorUtils.provideStockViewModelFactory(context))
    NavHost(navController = navController, startDestination = Screens.Home.route) {
        composable(route = Screens.Home.route) {
            HomeScreen(navController, stockViewModel)
        }
        composable(route = Screens.StockDetail.route) { backStackEntry ->
            val symbol = requireNotNull(backStackEntry.arguments?.getString("symbol"))
            StockDetailScreen(navController, symbol)
        }
        composable(route = Screens.StockNews.route) {
            StockNewsScreen(navController)
        }
        composable(route = Screens.Zakat.route) {
            ZakatScreen(navController)
        }
        composable(route = Screens.StockScreener.route) {
            StockScreenerScreen(navController)
        }
    }
}