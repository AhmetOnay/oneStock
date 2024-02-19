package com.example.onestock.repositories

import androidx.lifecycle.MutableLiveData
import com.example.onestock.api.ApiService
import com.example.onestock.models.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response


class DataRepository(private val FMPApi: ApiService.FMPApi) {

    val timeSeriesData = MutableLiveData<TimeSeries>()
    val stocksListData = MutableLiveData<List<StockInfo>>()
    val mostActiveData = MutableLiveData<List<Quote>>()
    var balanceSheetData = MutableLiveData<BalanceSheet>()
    var quoteData = MutableLiveData<Quote>()
    var generalSearchData = MutableLiveData<List<StockInfo>>()
    var stockScreenerSearchData = MutableLiveData<List<Screener>>()


    val apiKey = "5qrH2plVtrYf8zlY8RxQLo16b0xgaOau"

    fun fetchTimeSeries(symbols: String, interval: String) {
        FMPApi.getTimeSeries(symbols, interval, apiKey)
            .enqueue(object : Callback<TimeSeries> {
                override fun onResponse(
                    call: Call<TimeSeries>, response: Response<TimeSeries>
                ) {
                    if (response.isSuccessful) {
                        print(response.body())
                        response.body()?.let { responseBody ->
                            timeSeriesData.postValue(responseBody)
                        }
                    } else {
                        print("error: fetchTimeSeries")
                    }
                }

                override fun onFailure(call: Call<TimeSeries>, t: Throwable) {
                    print(call)
                }
            })
    }

    fun fetchStocksList() {
        FMPApi.getStocksList(apiKey).enqueue(object : Callback<List<StockInfo>> {
            override fun onResponse(
                call: Call<List<StockInfo>>, response: Response<List<StockInfo>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { responseBody ->
                        stocksListData.postValue(responseBody)
                    }
                } else {
                    print("error: fetchStocksList")
                }
            }

            override fun onFailure(call: Call<List<StockInfo>>, t: Throwable) {
                print(call);
            }
        })
    }
    fun fetchMostActive() {
        FMPApi.getMostActive(apiKey).enqueue(object : Callback<List<Quote>> {
            override fun onResponse(
                call: Call<List<Quote>>, response: Response<List<Quote>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { responseBody ->
                        mostActiveData.postValue(responseBody)
                    }
                } else {
                    print("error: fetchMostActive")
                }
            }

            override fun onFailure(call: Call<List<Quote>>, t: Throwable) {
                print(call);
            }
        })
    }
    fun fetchGeneralSearch(symbol: String){
        FMPApi.getGeneralSearch(symbol, apiKey).enqueue(object : Callback<List<StockInfo>> {
            override fun onResponse(
                call: Call<List<StockInfo>>, response: Response<List<StockInfo>>
            ) {
                if (response.isSuccessful) {
                    print(response.body())
                    var s = response.body().toString();
                    print(s);
                    response.body()?.let { responseBody ->
                        if(responseBody.isNotEmpty()){
                            generalSearchData.postValue(responseBody)
                        }
                    }
                } else {
                    print("error: fetchGeneralSearch")
                }
            }
            override fun onFailure(call: Call<List<StockInfo>>, t: Throwable) {
                print(call)
            }
        })
    }

    fun fetchBalanceSheet(symbol: String) {
        FMPApi.getBalanceSheet(symbol, apiKey).enqueue(object : Callback<List<BalanceSheet>> {
                override fun onResponse(
                    call: Call<List<BalanceSheet>>, response: Response<List<BalanceSheet>>
                ) {
                    if (response.isSuccessful) {
                        print(response.body())
                        var s = response.body().toString();
                        print(s);
                        response.body()?.let { responseBody ->
                            if(responseBody.isNotEmpty()){
                                balanceSheetData.postValue(responseBody?.get(0))
                            }
                        }
                    } else {
                        print("error: fetchBalanceSheet")
                    }
                }

                override fun onFailure(call: Call<List<BalanceSheet>>, t: Throwable) {
                    print(call)
                }
            })
    }

    fun fetchQuote(symbol: String) {
        FMPApi.getQuote(symbol, apiKey).enqueue(object : Callback<List<Quote>> {
            override fun onResponse(
                call: Call<List<Quote>>, response: Response<List<Quote>>
            ) {
                if (response.isSuccessful) {
                    print(response.body())
                    var s = response.body().toString();
                    print(s);
                    response.body()?.let { responseBody ->
                        quoteData.postValue(responseBody?.get(0))
                    }
                } else {
                    print("error: fetchQuote")
                }
            }

            override fun onFailure(call: Call<List<Quote>>, t: Throwable) {
                print(call)
            }
        })
    }

    suspend fun fetchBalanceSheet2(symbol: String): BalanceSheet? {
        return try {
            val response = FMPApi.getBalanceSheet2(symbol, apiKey)
            return response.body()?.firstOrNull()
        } catch (e: HttpException) {
            print("HTTP error: fetchBalanceSheet")
            null
        } catch (e: Exception) {
            print(e)
            null
        }
    }

    suspend fun fetchQuote2(symbol: String): Quote? {
        return try {
            val response = FMPApi.getQuote2(symbol, apiKey)
            if (response.isSuccessful) {
                response.body()?.firstOrNull()
            } else {
                print("error: fetchQuote2")
                null
            }
        } catch (e: Exception) {
            print(e)
            null
        }
    }

    fun fetchScreener(
        country: String?,
        industry: String?,
        marketCapMoreThan: Long?,
        marketCapLowerThan: Long?,
        dividendMoreThan: Double?,
        dividendLowerThan: Double?,
        volumeMoreThan: Long?,
        volumeLowerThan: Long?
    ) {
        FMPApi.getScreener(
            marketCapMoreThan = marketCapMoreThan,
            marketCapLowerThan = marketCapLowerThan,
            priceMoreThan = null,
            priceLowerThan = null,
            betaMoreThan = null,
            betaLowerThan = null,
            volumeMoreThan = volumeMoreThan,
            volumeLowerThan = volumeLowerThan,
            dividendMoreThan = dividendMoreThan,
            dividendLowerThan = dividendLowerThan,
            isEtf = null,
            isActivelyTrading = null,
            sector = null,
            industry = industry,
            country = country,
            exchange = null,
            limit = null,
            apiKey = apiKey
        ).enqueue(object : Callback<List<Screener>> {
            override fun onResponse(
                call: Call<List<Screener>>, response: Response<List<Screener>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { responseBody ->
                        if(responseBody.isNotEmpty()){
                            stockScreenerSearchData.postValue(responseBody)
                        }
                    }
                } else {
                    print("error: fetchScreener")
                }
            }

            override fun onFailure(call: Call<List<Screener>>, t: Throwable) {
                print("error: fetchScreener onFailure")
            }
        })
    }


    suspend fun fetchScreener2(
        country: String?,
        industry: String?,
        marketCapMoreThan: Long?
    ): List<Screener>? {
        return try {
            val response = FMPApi.getScreener2(
                marketCapMoreThan = marketCapMoreThan,
                marketCapLowerThan = null,
                priceMoreThan = null,
                priceLowerThan = null,
                betaMoreThan = null,
                betaLowerThan = null,
                volumeMoreThan = null,
                volumeLowerThan = null,
                dividendMoreThan = null,
                dividendLowerThan = null,
                isEtf = null,
                isActivelyTrading = null,
                sector = null,
                industry = industry,
                country = country,
                exchange = null,
                limit = null,
                apiKey = apiKey
            )
            if (response.isSuccessful) {
                response.body()
            } else {
                print("error: fetchScreener2")
                null
            }
        } catch (e: HttpException) {
            print("HTTP error: fetchScreener2")
            null
        } catch (e: Exception) {
            print(e)
            null
        }
    }
}

