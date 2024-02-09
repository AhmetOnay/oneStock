package com.example.onestock.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavHostController
import com.example.onestock.models.StockInfo
import com.example.onestock.navigation.Screens
import com.example.onestock.viewmodels.StockViewModel


@Composable
fun StocksList(navController: NavHostController, stockViewModel: StockViewModel) {
    val stocksListResponse by stockViewModel.stocksList.observeAsState()
    val stocksList = stocksListResponse ?: emptyList()

    LazyColumn {
        items(stocksList) { stock ->
            StockItem(stock) {
                navController.navigate(Screens.StockDetail.createRoute(stock.symbol))
            }
        }
    }
}

@Composable
fun StockItem(stock: StockInfo, onItemClick: () -> Unit) {
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
            Text(text = "Name: ${stock.name}", style = MaterialTheme.typography.h6)
            Text(
                text = "Symbol: ${stock.symbol}",
                style = MaterialTheme.typography.subtitle1
            )
            Text(
                text = "Exchange: ${stock.exchange}",
                style = MaterialTheme.typography.subtitle2
            )
        }
    }
}
