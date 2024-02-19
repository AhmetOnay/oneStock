package com.example.onestock.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.onestock.repositories.DataRepository

class StockScreenerViewModelFactory(
    private val dataRepository: DataRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StockScreenerViewModel::class.java)) {
            return StockScreenerViewModel(dataRepository = dataRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
