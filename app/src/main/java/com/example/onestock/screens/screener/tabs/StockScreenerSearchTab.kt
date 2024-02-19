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
    val selectedCountryOption = remember { mutableStateOf("US") }
    val selectedIndustryOption = remember { mutableStateOf("software") }
    var marketCapMoreThan by remember { mutableStateOf("") }
    var marketCapLowerThan by remember { mutableStateOf("") }
    var dividendMoreThan by remember { mutableStateOf("") }
    var dividendLowerThan by remember { mutableStateOf("") }
    var volumeMoreThan by remember { mutableStateOf("") }
    var volumeLowerThan by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        TextOptionPicker(
            selectedOption = selectedCountryOption,
            modifier= Modifier.fillMaxWidth(),
            options = listOf("US", "UK"),
        )
        TextOptionPicker(
            selectedOption = selectedIndustryOption,
            modifier = Modifier.fillMaxWidth(),
            options = listOf("Autos", "Banks", "Banks—Diversified", "Beverages Non-Alcoholic", "software"),
        )
        TextField(
            value = marketCapMoreThan,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { marketCapMoreThan = it },
            label = { Text("Marketcap. higher than") },
        )
        TextField(
            value = marketCapLowerThan,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            onValueChange = { marketCapLowerThan = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Marketcap. Lower than") },
        )
        TextField(
            value = dividendMoreThan,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Decimal),
            onValueChange = { dividendMoreThan = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Dividend more than") },
        )
        TextField(
            value = dividendLowerThan,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Decimal),
            onValueChange = { dividendLowerThan = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Dividend lower than") },
        )
        TextField(
            value = volumeMoreThan,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            onValueChange = { volumeMoreThan = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("volume more than") },
        )
        TextField(
            value = volumeLowerThan,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            onValueChange = { volumeLowerThan = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("volume lower than") },
        )
        Button(
            onClick = {
                val marketCapMore = marketCapMoreThan.toLongOrNull()
                val marketCapLower = marketCapLowerThan.toLongOrNull()
                val dividendMore = dividendMoreThan.replace(",", ".").toDoubleOrNull()
                val dividendLower = dividendLowerThan.replace(",", ".").toDoubleOrNull()
                val volumeMore = volumeMoreThan.toLongOrNull()
                val volumeLower = volumeLowerThan.toLongOrNull()

                viewModel.search(
                    selectedCountryOption.value,
                    selectedIndustryOption.value,
                    marketCapMore,
                    marketCapLower,
                    dividendMore,
                    dividendLower,
                    volumeMore,
                    volumeLower
                )

            }, modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Search")
        }
    }
}



