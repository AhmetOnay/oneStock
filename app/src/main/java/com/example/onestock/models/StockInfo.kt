package com.example.onestock.models

data class StocksList(
    val data: List<StockInfo>
)

data class StockInfo(
    val symbol: String,
    val name: String,
    val price: Double,
    val exchange: String,
    val exchangeShortName: String,
    val type: String,
    val currency: String,
    val stockExchange: String
)