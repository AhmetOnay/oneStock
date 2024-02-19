package com.example.onestock.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onestock.common.enums.InvestOption
import com.example.onestock.models.BalanceSheet
import com.example.onestock.models.Quote
import com.example.onestock.repositories.DataRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ZakatViewModel(private val dataRepository: DataRepository) : ViewModel() {
    private var balanceSheetData: BalanceSheet? = null
    private var quoteData: Quote? = null
    var amountToDonate = mutableStateOf("0")

    fun calculateZakat(selectedNumber: Long, selectedOption2: String, stockSymbol: String) {
        viewModelScope.launch {
            // Fetch balance sheet and quote data asynchronously
            val balanceSheetDeferred = async { dataRepository.fetchBalanceSheet2(stockSymbol) }
            val quoteDeferred = async { dataRepository.fetchQuote2(stockSymbol) }

            balanceSheetData = balanceSheetDeferred.await()
            quoteData = quoteDeferred.await()

            val result = if (selectedOption2 == InvestOption.SHORT.id) {
                calculateZakatBasedOnShortTerm(selectedNumber)
            } else {
                calculateZakatBasedOnLongTerm(selectedNumber)
            }
            amountToDonate.value = "%.2f ${balanceSheetData?.reportedCurrency ?: ""}".format(result)
        }
    }

    private fun calculateZakatBasedOnShortTerm(quantity: Long): Double {
        val price = quoteData?.price ?: 0.0
        return ((price * quantity) / 40).toDouble()
    }

    private fun calculateZakatBasedOnLongTerm(quantity: Long): Double {
        val price = quoteData?.price ?: 0.0
        val totalAssets = balanceSheetData?.totalAssets ?: 0L
        val totalCurrentAssets = balanceSheetData?.totalCurrentAssets ?: 0L
        val ratio = (totalCurrentAssets.toDouble() / totalAssets.toDouble())

        return (((price * quantity) * ratio) / 40)
    }
}