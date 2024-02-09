package com.example.onestock.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import com.example.onestock.models.*
import com.example.onestock.repositories.DataRepository

class StockViewModel(private val dataRepository: DataRepository) : ViewModel() {

    val stockData: LiveData<TimeSeries> = dataRepository.timeSeriesData
    val stocksListData: LiveData<List<StockInfo>> = dataRepository.stocksListData
    val mostActiveData: LiveData<List<Quote>> = dataRepository.mostActiveData

    var generalSearchData: LiveData<List<StockInfo>> = dataRepository.generalSearchData



    init {
        //getStocksList()
        getMostActive()
    }

    fun getStockData(symbols: String, interval: String) {
        dataRepository.fetchTimeSeries(symbols, interval)
    }

    private fun getStocksList() {
        dataRepository.fetchStocksList()
    }

    private fun getMostActive(){
        dataRepository.fetchMostActive()
    }

    fun generalSearch(txt: String){
        if (txt.isNotBlank()) {
            dataRepository.fetchGeneralSearch(txt)
        }
    }



}

