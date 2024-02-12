package com.example.onestock.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import com.example.onestock.models.BalanceSheet
import com.example.onestock.models.Quote
import com.example.onestock.widgets.QuoteWidget

@Composable
fun DashboardTab(balanceSheetData: BalanceSheet?, quoteData: Quote?, innerPadding: PaddingValues) {
    Column(modifier = androidx.compose.ui.Modifier.padding(innerPadding)) {
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