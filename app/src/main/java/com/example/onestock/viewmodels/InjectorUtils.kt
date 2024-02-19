package com.example.onestock.viewmodels

import RetrofitClient
import android.content.Context
import com.example.onestock.data.OneStockDatabase
import com.example.onestock.repositories.DataRepository
import com.example.onestock.repositories.StockNewsRepository
import com.example.onestock.repositories.StockRepository

object InjectorUtils {
    private fun getDataRepository(): DataRepository {
        val fmpApi = RetrofitClient.fmpApiService
        return DataRepository(fmpApi)
    }

    private fun getStockRepository(context: Context): StockRepository  {
        return StockRepository.getInstance(OneStockDatabase.getDatabase(context.applicationContext).stockDao())
    }

    fun getStockNewsRepository(): StockNewsRepository  {
        val marketauxApi = RetrofitClient.marketauxApiService
        return StockNewsRepository(marketauxApi)
    }

    fun provideStockNewsViewModelFactory(): StockNewsViewModelFactory  {
        val stockNewsRepository = getStockNewsRepository()
        return StockNewsViewModelFactory(stockNewsRepository)
    }

    fun provideStockViewModelFactory(context: Context): StockViewModelFactory {
        val repository = getDataRepository()
        val stockRepository = getStockRepository(context)
        return StockViewModelFactory(repository, stockRepository)
    }

    fun provideStockDetailViewModelFactory(context: Context, symbol: String): StockDetailViewModelFactory {
        val dataRepository = getDataRepository()
        val stockRepository = getStockRepository(context)
        return StockDetailViewModelFactory(dataRepository, stockRepository, symbol)
    }
    fun provideZakatViewModelFactory(): ZakatViewModelFactory {
        val dataRepository = getDataRepository()
        return ZakatViewModelFactory(dataRepository)
    }

    fun provideStockScreenerViewModelFactory(): StockScreenerViewModelFactory {
        val dataRepository = getDataRepository()
        return StockScreenerViewModelFactory(dataRepository)
    }
}
