package com.example.onestock.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onestock.models.Screener
import com.example.onestock.repositories.DataRepository
import kotlinx.coroutines.launch

class StockScreenerViewModel(private val dataRepository: DataRepository) : ViewModel() {
    var stockScreenerSearchData: LiveData<List<Screener>> = dataRepository.stockScreenerSearchData
    var searchCompleted = mutableStateOf(false)

    fun search(country:String, industry:String, marketCapMoreThan: Long){
        viewModelScope.launch {
            dataRepository.fetchScreener(country, industry, marketCapMoreThan)
            searchCompleted.value = true
        }
    }
}