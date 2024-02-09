package com.example.onestock.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.onestock.models.BalanceSheet
import com.example.onestock.models.Quote
import com.example.onestock.repositories.DataRepository

class StockDetailViewModel(private val dataRepository: DataRepository): ViewModel() {
    val quoteData: LiveData<Quote> = dataRepository.quoteData
    val balanceSheetData: LiveData<BalanceSheet> = dataRepository.balanceSheetData

    fun getBalanceSheetInfo(txt: String){
        dataRepository.fetchBalanceSheet(txt)
    }
    fun getQuoteInfo(txt: String){
        dataRepository.fetchQuote(txt)
    }
}