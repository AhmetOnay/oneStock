package com.example.onestock.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.onestock.navigation.Screens
import com.example.onestock.repositories.StockRepository
import com.example.onestock.viewmodels.StockViewModel
import com.example.onestock.widgets.CustomScaffold
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavHostController, stockViewModel: StockViewModel) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val tabs = listOf("Watchlist", "Trending", "Search")
    var selectedTabIndex by remember { mutableStateOf(1) }


    //stockViewModel.getStockData("AAPL", "1min")

    //val stockData by stockViewModel.stockData.observeAsState()


    CustomScaffold(
        navController = navController,
        topBarTitle = "One Stock",
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            TabRow(
                selectedTabIndex = selectedTabIndex,
                backgroundColor = MaterialTheme.colors.secondary,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                        color = MaterialTheme.colors.onPrimary,
                        height = 3.dp
                    )
                }
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        text = { Text(title, color = MaterialTheme.colors.onPrimary) },
                        selected = index == selectedTabIndex,
                        onClick = { selectedTabIndex = index }
                    )
                }
            }

            when (selectedTabIndex) {
                0 -> WatchlistTab(navController = navController, stockViewModel = stockViewModel)
                1 -> MostActiveTab(navController, stockViewModel)
                2 -> StockSearchTab(navController, stockViewModel)
            }
        }
    }
}


@Composable
fun StockList(category: String, txt: String) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("List of $category", style = MaterialTheme.typography.h6)
        Text("$txt", style = MaterialTheme.typography.h6)
    }
}
