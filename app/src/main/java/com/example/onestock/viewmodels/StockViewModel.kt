package com.example.onestock.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.onestock.models.*
import com.example.onestock.repositories.DataRepository
import okhttp3.ResponseBody

class StockViewModel(private val dataRepository: DataRepository) : ViewModel() {

    val stockData: LiveData<TimeSeries> = dataRepository.timeSeriesData
    val stocksList: LiveData<List<StockInfo>> = dataRepository.stocksListData
    val balanceSheet: LiveData<BalanceSheet> = dataRepository.balanceSheetData
    val quote: LiveData<Quote> = dataRepository.quoteData
    var generalSearchResults: LiveData<List<StockInfo>> = dataRepository.generalSearchData
    val apiKey = "5qrH2plVtrYf8zlY8RxQLo16b0xgaOau"

    init {
        fetchStocksList()
    }

    fun getStockData(symbols: String, interval: String) {
        dataRepository.fetchTimeSeries(symbols, interval, apiKey)
    }

    fun fetchStocksList() {
        dataRepository.fetchStocksList(apiKey)
    }

    fun generalSearch(txt: String){
        if (txt.isNotBlank()) {
            dataRepository.fetchGeneralSearch(txt, apiKey)
        }
    }

    fun getBalanceSheetInfo(txt: String){
        dataRepository.fetchBalanceSheet(txt, apiKey)
    }
    fun getQuoteInfo(txt: String){
        dataRepository.fetchQuote(txt, apiKey)
    }

}

