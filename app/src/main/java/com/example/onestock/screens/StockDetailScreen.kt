package com.example.onestock.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
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
import com.example.onestock.viewmodels.InjectorUtils
import com.example.onestock.viewmodels.StockDetailViewModel
import com.example.onestock.widgets.QuoteWidget
import kotlinx.coroutines.launch

@Composable
fun StockDetailScreen(navController: NavHostController, symbol: String) {
    val currentContext = LocalContext.current
    val viewModel: StockDetailViewModel = viewModel(
        factory = InjectorUtils.provideStockDetailScreenViewModelFactory(
            currentContext
        )
    )
    val balanceSheetData by viewModel.balanceSheetData.observeAsState()
    val quoteData by viewModel.quoteData.observeAsState()
    val stock by viewModel.getStock(symbol).collectAsState(initial = null)
    val isFavorite by remember { derivedStateOf { stock != null } }
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
                            if (!isFavorite) {
                                viewModel.saveStock(symbol)
                            } else {
                                viewModel.deleteStock(symbol)
                            }
                        }
                    }) {
                        Icon(
                            imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                            contentDescription = if (isFavorite) "Remove from Favorites" else "Add to Favorites"
                        )
                    }
                })
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Column(modifier = Modifier.padding(16.dp)) {
                quoteData?.let { quote ->
                    QuoteWidget(quote = quote)
                    Divider(modifier = Modifier.padding(vertical = 16.dp))
                }

                balanceSheetData?.let { balanceSheet ->
                    BalanceSheetWidget(balanceSheet = balanceSheet)
                }
            }
        }
    }
}
