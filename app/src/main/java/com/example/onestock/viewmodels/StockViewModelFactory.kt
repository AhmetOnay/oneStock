package com.example.onestock.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.onestock.repositories.DataRepository
import com.example.onestock.repositories.StockRepository

class StockViewModelFactory(
    private val dataRepository: DataRepository,
    private val stockRepository: StockRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StockViewModel::class.java)) {
            return StockViewModel(dataRepository, stockRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
