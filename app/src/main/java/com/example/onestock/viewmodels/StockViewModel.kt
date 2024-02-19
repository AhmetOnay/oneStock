package com.example.onestock.viewmodels

import androidx.lifecycle.*
import com.example.onestock.models.*
import com.example.onestock.repositories.DataRepository
import com.example.onestock.repositories.StockRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StockViewModel(private val dataRepository: DataRepository, private val stockRepository: StockRepository) : ViewModel() {

    val stockData: LiveData<TimeSeries> = dataRepository.timeSeriesData
    val savedStocksQuotesLiveData = MutableLiveData<List<Quote>>()
    //val stocksListData: LiveData<List<StockInfo>> = dataRepository.stocksListData
    val mostActiveData: LiveData<List<Quote>> = dataRepository.mostActiveData
    var generalSearchData: LiveData<List<StockInfo>> = dataRepository.generalSearchData
    private val _symbols = MutableStateFlow(listOf<String>())
    private val symbols: StateFlow<List<String>> = _symbols.asStateFlow()


    init {
        //getMostActive()
       /* viewModelScope.launch {
            stockRepository.getAllStocksSymbols().collect { symbols ->
                _symbols.value = symbols
                getSavedStocksQuotesLiveData()
            }
        }*/
        //getStocksList()
    }

    fun getStockData(symbols: String, interval: String) {
        dataRepository.fetchTimeSeries(symbols, interval)
    }

    private fun getStocksList() {
        dataRepository.fetchStocksList()
    }

    private fun getSavedStocksQuotesLiveData() {
        viewModelScope.launch {
            val quotes = mutableListOf<Quote>()
            symbols.value.forEach { symbol ->
                val quote = dataRepository.fetchQuote2(symbol)
                quote?.let { quotes.add(it) }
            }
            savedStocksQuotesLiveData.postValue(quotes)
        }
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

