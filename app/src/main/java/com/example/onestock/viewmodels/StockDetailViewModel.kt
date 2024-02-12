package com.example.onestock.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onestock.models.BalanceSheet
import com.example.onestock.models.Quote
import com.example.onestock.models.Stock
import com.example.onestock.repositories.DataRepository
import com.example.onestock.repositories.StockRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.text.DateFormatSymbols

class StockDetailViewModel(
    private val dataRepository: DataRepository,
    private val stockRepository: StockRepository
) : ViewModel() {
    val balanceSheetData: LiveData<BalanceSheet> = dataRepository.balanceSheetData
    val quoteData: LiveData<Quote> = dataRepository.quoteData
    fun getBalanceSheetInfo(txt: String) {
        dataRepository.fetchBalanceSheet(txt)
    }

    fun getQuoteInfo(txt: String) {
        return dataRepository.fetchQuote(txt)
    }

    suspend fun saveStock(symbol: String) {
        var stock = Stock(symbol, "", "")
        stockRepository.addStock(stock);
    }

    fun deleteStock(symbol: String) {

    }

    fun getStock(symbol: String): Flow<Stock?> {
        return stockRepository.getStockBySymbol(symbol)
    }

}