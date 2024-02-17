package com.example.onestock.viewmodels

import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.onestock.models.News
import com.example.onestock.repositories.StockNewsRepository
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters

class StockNewsViewModel(private val stockNewsRepository: StockNewsRepository): ViewModel() {
    val stockNewsData: MutableLiveData<News> = stockNewsRepository.newsData

    init {
        getNews()
    }

    private fun getNews(){
        val todayDate = LocalDate.now()
        val formattedDateTime = todayDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "T00:00"
        stockNewsRepository.fetchNews("us", true, 10, formattedDateTime)
    }
}