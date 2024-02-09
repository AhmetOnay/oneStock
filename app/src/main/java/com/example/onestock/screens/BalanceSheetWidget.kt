package com.example.onestock.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.onestock.models.BalanceSheet
import java.text.NumberFormat
import java.util.Locale


@Composable
fun BalanceSheetWidget(balanceSheet: BalanceSheet) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState()).padding(16.dp)) {
        DateHeaderView(date = balanceSheet.date)

        SectionTitle(title = "Assets")
        ListItemView(label = "Current Assets", value = balanceSheet.cashAndCashEquivalents)
        ListItemView(label = "Short Term \n Investments", value = balanceSheet.shortTermInvestments)
        ListItemView(label = "Net Receivables", value = balanceSheet.netReceivables)
        TotalView(label = "Total Assets", value = balanceSheet.totalAssets)

        SectionTitle(title = "Liabilities")
        ListItemView(label = "Account Payables", value = balanceSheet.accountPayables)
        ListItemView(label = "Short Term Debt", value = balanceSheet.shortTermDebt)
        TotalView(label = "Total Liabilities", value = balanceSheet.totalLiabilities)

        SectionTitle(title = "Equity")
        ListItemView(label = "Common Stock", value = balanceSheet.commonStock)
        ListItemView(label = "Retained Earnings", value = balanceSheet.retainedEarnings)
        TotalView(label = "Total Equity", value = balanceSheet.totalEquity)
    }
}

@Composable
fun DateHeaderView(date: String) {
    Text(
        text = "BalanceSheet: $date",
        style = MaterialTheme.typography.h6,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
    Divider()
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.h6,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
    )
}

@Composable
fun ListItemView(label: String, value: Long) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, style = MaterialTheme.typography.body1)
        Text(formatNumber(value), style = MaterialTheme.typography.body1)
    }
}

@Composable
fun TotalView(label: String, value: Long) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = formatNumber(value),
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold
        )
    }
    Divider()
}

fun formatNumber(number: Long): String {
    // Format the number as needed, e.g., with commas
    return NumberFormat.getNumberInstance(Locale.getDefault()).format(number)
}

