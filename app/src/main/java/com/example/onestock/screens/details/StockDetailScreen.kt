package com.example.onestock.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.onestock.screens.details.tabs.NoteTab
import com.example.onestock.viewmodels.InjectorUtils
import com.example.onestock.viewmodels.StockDetailViewModel
import kotlinx.coroutines.launch

@Composable
fun StockDetailScreen(navController: NavHostController, symbol: String) {
    val currentContext = LocalContext.current
    val viewModel: StockDetailViewModel = viewModel(
        factory = InjectorUtils.provideStockDetailScreenViewModelFactory(
            currentContext, symbol
        )
    )
    val balanceSheetData by viewModel.balanceSheetData.observeAsState()
    val quoteData by viewModel.quoteData.observeAsState()

    val stock = viewModel.stock
    val isInWatchlist by remember(stock) { derivedStateOf { stock.symbol.isNotEmpty() } }

    var selectedTabIndex by remember { mutableStateOf(0) }
    var tabs = listOf("Dashboard")
    if (stock.symbol != "") {
        tabs = listOf("Dashboard", "Note")
    }
    val scope = rememberCoroutineScope()

    LaunchedEffect(symbol) {
        viewModel.getBalanceSheetInfo(symbol)
        viewModel.getQuoteInfo(symbol)
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Stock Detail Screen") },
                navigationIcon = if (navController.previousBackStackEntry != null) {
                    {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                } else {
                    null
                },
                actions = {
                    IconButton(onClick = {
                        scope.launch {
                            if (!isInWatchlist) {
                                viewModel.saveStock(symbol)
                            } else {
                                viewModel.deleteStock()
                            }
                        }
                    }) {
                        Icon(
                            imageVector = if (isInWatchlist) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                            contentDescription = if (isInWatchlist) "Remove from Favorites" else "Add to Favorites"
                        )
                    }
                })
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
                0 -> DashboardTab(balanceSheetData, quoteData, innerPadding)
                1 -> stock?.let { NoteTab(stockDetailViewModel = viewModel) }
            }
        }
    }
}
