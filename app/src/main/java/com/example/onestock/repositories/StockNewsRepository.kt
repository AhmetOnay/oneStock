package com.example.onestock.repositories

import androidx.lifecycle.MutableLiveData
import com.example.onestock.api.ApiService
import com.example.onestock.models.News
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StockNewsRepository(private val marketaux: ApiService.MarketauxApi) {
    val apiKey = "ywiRvhvopmrvF3rbsOMZMsyVbnovP10OjJhqZlbg"
    var newsData = MutableLiveData<News>()

    fun fetchNews(countries: String, filterEntities: Boolean, limit: Int, publishedAfter : String) {
        marketaux.getNews(countries = countries,
            filterEntities = filterEntities,
            limit = limit,
            publishedAfter = publishedAfter,
            apiToken = apiKey)
            .enqueue(object : Callback<News> {
                override fun onResponse(
                    call: Call<News>, response: Response<News>
                ) {
                    if (response.isSuccessful) {
                        print(response.body())
                        response.body()?.let { responseBody ->
                            newsData.postValue(responseBody)
                        }
                    } else {
                        print("error: fetchNews")
                    }
                }

                override fun onFailure(call: Call<News>, t: Throwable) {
                    print(call)
                }
            })
    }

    fun fetchNewsSynchronously(countries: String, filterEntities: Boolean, limit: Int, publishedAfter: String): News? {
        return try {
            val response = marketaux.getNews(countries = countries,
                filterEntities = filterEntities,
                limit = limit,
                publishedAfter = publishedAfter,
                apiToken = apiKey).execute()
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

}