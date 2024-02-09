package com.example.onestock.viewmodels

import com.example.onestock.api.ApiService
import com.example.onestock.repositories.DataRepository
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

    fun provideStockViewModelFactory(): StockViewModelFactory {
        val repository = getDataRepository()
        return StockViewModelFactory(repository)
    }

    fun provideStockDetailScreenViewModelFactory(): StockDetailViewModelFactory {
        val repository = getDataRepository()
        return StockDetailViewModelFactory(repository)
    }

}
