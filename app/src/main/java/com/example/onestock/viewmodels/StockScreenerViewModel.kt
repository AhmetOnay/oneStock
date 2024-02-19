package com.example.onestock.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onestock.models.Screener
import com.example.onestock.repositories.DataRepository
import kotlinx.coroutines.launch

class StockScreenerViewModel(private val dataRepository: DataRepository) : ViewModel() {
    var _stockScreenerSearchData: MutableLiveData<List<Screener>> = dataRepository.stockScreenerSearchData
    var stockScreenerSearchData: LiveData<List<Screener>> = _stockScreenerSearchData
    var searchCompleted = mutableStateOf(false)

    fun search(country:String, industry:String, marketCapMoreThan: Long){
        viewModelScope.launch {
            dataRepository.fetchScreener(country, industry, marketCapMoreThan)
            searchCompleted.value = true
        }
    }

    fun sortAscendingByMarketCap() {
        val sortedList = stockScreenerSearchData.value?.sortedBy { it.companyName }
        _stockScreenerSearchData.postValue(sortedList)
    }

    fun sortDescendingByMarketCap() {
        val sortedList = stockScreenerSearchData.value?.sortedByDescending { it.companyName }
        _stockScreenerSearchData.postValue(sortedList)
    }

    fun sortStocksByHighestMarketCap() {
        val sortedList = _stockScreenerSearchData.value?.sortedByDescending { it.marketCap }
        _stockScreenerSearchData.postValue(sortedList)
    }
}