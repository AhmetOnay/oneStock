package com.example.onestock.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.onestock.viewmodels.StockViewModel
import com.example.onestock.widgets.QuoteWidget

@Composable
fun StockDetailScreen(viewModel: StockViewModel, symbol: String) {
    val balanceSheetData by viewModel.balanceSheet.observeAsState()
    val quoteData by viewModel.quote.observeAsState()

    LaunchedEffect(symbol) {
        viewModel.getBalanceSheetInfo(symbol)
        viewModel.getQuoteInfo(symbol)
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Stock Detail Screen") })
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
