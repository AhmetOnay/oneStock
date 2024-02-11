package com.example.onestock.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.onestock.viewmodels.InjectorUtils
import com.example.onestock.viewmodels.StockViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavHostController, stockViewModel: StockViewModel) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val tabs = listOf("Watchlist", "Trending", "Search")
    var selectedTabIndex by remember { mutableStateOf(1) }



    //stockViewModel.getStockData("AAPL", "1min")

    val stockData by stockViewModel.stockData.observeAsState()


    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text("One Stock") },
                backgroundColor = MaterialTheme.colors.primary,
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }) {
                        Icon(Icons.Filled.Menu, contentDescription = "Menu")
                    }
                }
            )
        },
        drawerContent = {
            DrawerHeader()
            DrawerBody()
        }
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
                0 -> stockData?.meta?.currency?.let { StockList("Watchlist", it) }
                1 -> StocksList(navController, stockViewModel)
                2 -> StockSearchScreen(navController, stockViewModel)
            }
        }
    }
}

@Composable
fun DrawerHeader() {
    Text("One Stock", style = MaterialTheme.typography.h6, modifier = Modifier.padding(16.dp))
}

@Composable
fun DrawerBody() {
    Text("News", modifier = Modifier.padding(16.dp))
    Text("Zakat", modifier = Modifier.padding(16.dp))
}

@Composable
fun StockList(category: String, txt: String) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("List of $category", style = MaterialTheme.typography.h6)
        Text("$txt", style = MaterialTheme.typography.h6)
    }
}
