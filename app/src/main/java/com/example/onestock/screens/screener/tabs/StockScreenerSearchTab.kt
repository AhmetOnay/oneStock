package com.example.onestock.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.onestock.viewmodels.StockScreenerViewModel
import com.example.onestock.widgets.TextOptionPicker

@Composable
fun StockSearchTab(navController: NavHostController, viewModel: StockScreenerViewModel) {
    val selectedCountryOption = remember { mutableStateOf("us") }
    val selectedIndustryOption = remember { mutableStateOf("software") }
    var marketCapMoreThan by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        TextOptionPicker(
            selectedOption = selectedCountryOption,
            options = listOf("US", "UK"),
        )
        TextOptionPicker(
            selectedOption = selectedIndustryOption,
            options = listOf("Autos", "Banks", "Banks—Diversified", "Beverages Non-Alcoholic"),
        )
        TextField(
            value = marketCapMoreThan,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            onValueChange = { marketCapMoreThan = it },
            label = { Text("Marketcap higher than") },
        )
        Button(
            onClick = {
                viewModel.search(selectedCountryOption.value, selectedIndustryOption.value, marketCapMoreThan.toLong())
            }, modifier = Modifier
                .width(150.dp)
                .height(50.dp)
        ) {
            Text("Search")
        }
    }
}



