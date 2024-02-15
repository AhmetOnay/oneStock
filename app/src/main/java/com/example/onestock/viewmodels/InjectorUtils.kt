package com.example.onestock.viewmodels

import android.content.Context
import com.example.onestock.api.ApiService
import com.example.onestock.data.OneStockDatabase
import com.example.onestock.repositories.DataRepository
import com.example.onestock.repositories.StockRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object InjectorUtils {

    private fun getFMPApi(): ApiService.FMPApi {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://financialmodelingprep.com/api/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiService.FMPApi::class.java)
    }

    private fun getDataRepository(): DataRepository {
        val fmpApi = getFMPApi()
        return DataRepository(fmpApi)
    }

    private fun getStockRepository(context: Context): StockRepository  {
        return StockRepository.getInstance(OneStockDatabase.getDatabase(context.applicationContext).stockDao())
    }

    fun provideStockViewModelFactory(context: Context): StockViewModelFactory {
        val repository = getDataRepository()
        val stockRepository = getStockRepository(context)
        return StockViewModelFactory(repository, stockRepository)
    }

    fun provideStockDetailScreenViewModelFactory(context: Context, symbol: String): StockDetailViewModelFactory {
        val repository = getDataRepository()
        val stockRepository = getStockRepository(context)
        return StockDetailViewModelFactory(repository, stockRepository, symbol)
    }

}
