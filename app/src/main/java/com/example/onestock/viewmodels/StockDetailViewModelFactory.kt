package com.example.onestock.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.onestock.repositories.DataRepository
import com.example.onestock.repositories.StockRepository

class StockDetailViewModelFactory(
    private val dataRepository: DataRepository,
    private val stockRepository: StockRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StockDetailViewModel::class.java)) {
            return StockDetailViewModel(dataRepository = dataRepository, stockRepository =  stockRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
