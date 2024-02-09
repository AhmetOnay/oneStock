package com.example.onestock.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import com.example.onestock.models.*
import com.example.onestock.repositories.DataRepository

class StockViewModel(private val dataRepository: DataRepository) : ViewModel() {

    val stockData: LiveData<TimeSeries> = dataRepository.timeSeriesData
    val stocksListData: LiveData<List<StockInfo>> = dataRepository.stocksListData
    val mostActiveData: LiveData<List<Quote>> = dataRepository.mostActiveData
    val balanceSheetData: LiveData<BalanceSheet> = dataRepository.balanceSheetData
    val quoteData: LiveData<Quote> = dataRepository.quoteData
    var generalSearchData: LiveData<List<StockInfo>> = dataRepository.generalSearchData


    val apiKey = "5qrH2plVtrYf8zlY8RxQLo16b0xgaOau"

    init {
        //getStocksList()
        getMostActive()
    }

    fun getStockData(symbols: String, interval: String) {
        dataRepository.fetchTimeSeries(symbols, interval, apiKey)
    }

    private fun getStocksList() {
        dataRepository.fetchStocksList(apiKey)
    }

    private fun getMostActive(){
        dataRepository.fetchMostActive(apiKey)
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

