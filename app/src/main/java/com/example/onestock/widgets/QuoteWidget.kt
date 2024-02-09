package com.example.onestock.widgets

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.onestock.models.Quote

@Composable
fun QuoteWidget(quote: Quote) {
    Surface(modifier = Modifier.padding(16.dp)) {
        Card(
            elevation = 4.dp,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.padding(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "Stock Details", style = MaterialTheme.typography.h6)
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    QuoteDetail("Symbol", quote.symbol)
                    Spacer(modifier = Modifier.width(8.dp))
                    QuoteDetail("Name", quote.name)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    QuoteDetail("Price", quote.price.toString())
                    Spacer(modifier = Modifier.width(8.dp))
                    QuoteDetail("Change", "${quote.change} (${quote.changesPercentage}%)", isChangePositive(quote.change))
                }
            }
        }
    }
}

@Composable
fun QuoteDetail(label: String, value: String, isPositive: Boolean = true) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.border(1.dp, Color.LightGray).padding(8.dp)
    ) {
        Text(text = label, style = MaterialTheme.typography.caption)
        Text(
            text = value,
            color = if (isPositive) Color.Green else Color.Red,
            style = MaterialTheme.typography.body1
        )
    }
}

fun isChangePositive(change: Double): Boolean {
    return change >= 0
}
