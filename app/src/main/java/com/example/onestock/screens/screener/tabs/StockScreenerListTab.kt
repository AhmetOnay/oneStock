package com.example.onestock.screens.screener.tabs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.navigation.NavHostController
import com.example.onestock.models.Screener
import com.example.onestock.navigation.Screens
import com.example.onestock.viewmodels.StockScreenerViewModel


@Composable
fun StockScreenerListTab(
    navController: NavHostController,
    stockScreenerViewModel: StockScreenerViewModel
) {
    val stocksList by stockScreenerViewModel.stockScreenerSearchData.observeAsState(initial = emptyList())

    LazyColumn {
        items(stocksList) { stock ->
            StockScreenerItem(stock) {
                navController.navigate(Screens.StockDetail.createRoute(stock.symbol))
            }
        }
    }
}

@Composable
fun StockScreenerItem(stock: Screener, onItemClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onItemClick),
        elevation = 2.dp
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(text = "Name: ${stock.companyName}", style = MaterialTheme.typography.h6)
            Text(
                text = "Symbol: ${stock.symbol}",
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
}
