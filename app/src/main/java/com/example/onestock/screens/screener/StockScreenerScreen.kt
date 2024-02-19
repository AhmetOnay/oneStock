package com.example.onestock.screens.screener

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.onestock.widgets.CustomScaffold
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.onestock.screens.StockSearchTab
import com.example.onestock.screens.screener.tabs.StockScreenerListTab
import com.example.onestock.viewmodels.InjectorUtils
import com.example.onestock.viewmodels.StockScreenerViewModel


@Composable
fun StockScreenerScreen(navController: NavHostController) {
    val viewModel: StockScreenerViewModel = viewModel(
        factory = InjectorUtils.provideStockScreenerViewModelFactory()
    )
    val tabs = listOf("Search", "List")
    var selectedTabIndex by remember { mutableStateOf(0) }
    val searchCompleted by viewModel.searchCompleted
    LaunchedEffect(searchCompleted) {
        if (searchCompleted) {
            selectedTabIndex = 1
            viewModel.searchCompleted.value = false
        }
    }
    CustomScaffold(
        navController = navController,
        topBarTitle = "Stock Screener",
        overflowMenuContent = { OverflowMenu() }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            TabRow(
                selectedTabIndex = selectedTabIndex,
                backgroundColor = MaterialTheme.colors.secondary,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                        color = MaterialTheme.colors.onPrimary,
                        height = 3.dp
                    )
                }
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        text = { Text(title, color = MaterialTheme.colors.onPrimary) },
                        selected = index == selectedTabIndex,
                        onClick = { selectedTabIndex = index }
                    )
                }
            }

            when (selectedTabIndex) {
                0 -> StockSearchTab(navController, viewModel)
                1 -> StockScreenerListTab(navController = navController, stockScreenerViewModel = viewModel)
            }
        }
    }
}

@Composable
fun OverflowMenu() {
    var showMenu by remember { mutableStateOf(false) }

    IconButton(onClick = { showMenu = !showMenu }) {
        Icon(Icons.Default.MoreVert, contentDescription = "More")
    }

    DropdownMenu(
        expanded = showMenu,
        onDismissRequest = { showMenu = false }
    ) {
        DropdownMenuItem(onClick = {
            showMenu = false
        }) {
            Text("Sort Asc")
        }
        DropdownMenuItem(onClick = {
            showMenu = false
        }) {
            Text("Sort desc")
        }
        DropdownMenuItem(onClick = {
            showMenu = false
        }) {
            Text("Sort by Marketcap")
        }
        DropdownMenuItem(onClick = {
            showMenu = false
        }) {
            Text("Sort by Marketcap")
        }
    }
}