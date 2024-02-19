package com.example.onestock.screens.zakat

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.onestock.widgets.CustomScaffold
import com.example.onestock.widgets.NumberPicker
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.onestock.common.enums.InvestOption
import com.example.onestock.viewmodels.InjectorUtils
import com.example.onestock.viewmodels.ZakatViewModel
import com.example.onestock.widgets.TextOptionPicker


@Composable
fun ZakatScreen(navController: NavHostController) {
    val viewModel: ZakatViewModel = viewModel(
        factory = InjectorUtils.provideZakatViewModelFactory()
    )
    var stockSymbol by remember { mutableStateOf("") }
    val selectedOption2 = remember { mutableStateOf(InvestOption.SHORT.id) }
    val amountToDonate by viewModel.amountToDonate
    val selectedNumber = remember { mutableStateOf("1") }

    CustomScaffold(
        navController = navController,
        topBarTitle = "Zakat Calculator",
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            NumberPicker(
                selectedNumber = selectedNumber,
                numberRange = 1..100,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            TextOptionPicker(
                selectedOption = selectedOption2,
                options = listOf(InvestOption.SHORT.id, InvestOption.LONG.id),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            TextField(
                value = stockSymbol,
                onValueChange = { stockSymbol = it },
                label = { Text("Symbol") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            )
            Text(
                text = amountToDonate,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            Button(
                onClick = {
                    if(!stockSymbol.isNullOrBlank()){
                        viewModel.calculateZakat(
                            selectedNumber.value.toLong(),
                            selectedOption2.value,
                            stockSymbol
                        )
                    }
                }, modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Calculate")
            }
        }
    }
}