package com.example.onestock.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.onestock.repositories.StockNewsRepository

class StockNewsViewModelFactory(
    private val stockNewsRepository: StockNewsRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StockNewsViewModel::class.java)) {
            return StockNewsViewModel(stockNewsRepository = stockNewsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
