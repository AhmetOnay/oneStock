package com.example.onestock.screens.home.tabs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.onestock.models.StockInfo
import com.example.onestock.navigation.Screens
import com.example.onestock.viewmodels.StockViewModel
import kotlinx.coroutines.delay

@Composable
fun StockSearchTab(navController: NavHostController, stockViewModel: StockViewModel) {
    var searchText by remember { mutableStateOf("") }
    val searchResults by stockViewModel.generalSearchData.observeAsState()

    LaunchedEffect(searchText) {
        delay(1000)
        stockViewModel.generalSearch(searchText)
    }
    Column {
        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            label = { Text("Search Stock") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        LazyColumn {
            items(searchResults ?: emptyList()) { stock ->
                ListItem(stock = stock , onStockSelected = {
                    navController.navigate(Screens.StockDetail.createRoute(stock.symbol))
                })
            }
        }
    }
}


@Composable
fun ListItem(stock: StockInfo, onStockSelected: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { (onStockSelected(stock.symbol)) },
        elevation = 2.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Name: ${stock.name}", style = MaterialTheme.typography.h6)
            Text(text = "Symbol: ${stock.symbol}", style = MaterialTheme.typography.subtitle1)
        }
    }
}
