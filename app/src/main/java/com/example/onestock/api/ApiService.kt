package com.example.onestock.api
import com.example.onestock.models.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    interface FMPApi {
        @GET("time_series")
        fun getTimeSeries(
            @Query("symbol") symbol: String,
            @Query("interval") interval: String,
            @Query("apikey") apiKey: String
        ): Call<TimeSeries>

        @GET("stock/list")
        fun getStocksList(
            @Query("apikey") apiKey: String
        ): Call<List<StockInfo>>

        @GET("stock_market/actives")
        fun getMostActive(
            @Query("apikey") apiKey: String
        ): Call<List<Quote>>

        @GET("search")
        fun getGeneralSearch(
            @Query("query") query: String,
            @Query("apikey") apiKey: String
        ): Call<List<StockInfo>>

        @GET("quote/{symbol}")
        fun getQuote(
            @Path("symbol") symbol: String,
            @Query("apikey") apiKey: String
        ): Call<List<Quote>>

        @GET("quote/{symbol}")
        suspend fun getQuote2(
            @Path("symbol") symbol: String,
            @Query("apikey") apiKey: String
        ): Response<List<Quote>>

        @GET("balance-sheet-statement/{symbol}?period=annual")
        fun getBalanceSheet(
            @Path("symbol") symbol: String,
            @Query("apikey") apiKey: String
        ): Call<List<BalanceSheet>>
    }
}