package com.example.onestock.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.onestock.screens.HomeScreen
import com.example.onestock.screens.StockDetailScreen
import com.example.onestock.viewmodels.InjectorUtils
import com.example.onestock.viewmodels.StockDetailViewModel
import com.example.onestock.viewmodels.StockViewModel


@Composable
fun SetupNavGraph(
    navController: NavHostController,
    context: Context
) {
    val stockViewModel: StockViewModel =
        viewModel(factory = InjectorUtils.provideStockViewModelFactory(context))
    val stockDetailViewModel: StockDetailViewModel = viewModel(
        factory = InjectorUtils.provideStockDetailScreenViewModelFactory(
            context
        )
    )
    NavHost(navController = navController, startDestination = Screens.Home.route) {
        composable(route = Screens.Home.route) {
            HomeScreen(navController, stockViewModel)
        }
        composable(route = Screens.StockDetail.route) { backStackEntry ->
            val symbol = requireNotNull(backStackEntry.arguments?.getString("symbol"))
            StockDetailScreen(navController, symbol)
        }
    }
}