package com.example.onestock.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.onestock.models.BalanceSheet
import com.example.onestock.models.Quote
import com.example.onestock.models.Stock
import com.example.onestock.repositories.DataRepository
import com.example.onestock.repositories.StockRepository
import kotlinx.coroutines.flow.Flow
import java.text.DateFormatSymbols

class StockDetailViewModel(private val dataRepository: DataRepository, private val stockRepository: StockRepository): ViewModel() {
    val quoteData: LiveData<Quote> = dataRepository.quoteData
    val balanceSheetData: LiveData<BalanceSheet> = dataRepository.balanceSheetData

    fun getBalanceSheetInfo(txt: String){
        dataRepository.fetchBalanceSheet(txt)
    }
    fun getQuoteInfo(txt: String){
        dataRepository.fetchQuote(txt)
    }

    suspend fun saveStock(symbol: String){
        var stock = Stock(symbol, "", true, "")
        stockRepository.addStock(stock);
    }

    fun deleteStock(symbol: String){

    }

    fun getStock(symbol:String): Flow<Stock?> {
        return stockRepository.getStockBySymbol(symbol)
    }

}