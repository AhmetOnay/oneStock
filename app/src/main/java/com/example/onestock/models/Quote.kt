package com.example.onestock.models

data class Quote(
    val symbol: String,
    val name: String,
    val price: Double,
    val changesPercentage: Double,
    val change: Double,
    val dayLow: Double,
    val dayHigh: Double,
    val yearHigh: Double,
    val yearLow: Double,
    val marketCap: Long,
    val priceAvg50: Double,
    val priceAvg200: Double,
    val exchange: String,
    val volume: Long,
    val avgVolume: Long,
    val open: Double,
    val previousClose: Double,
    val eps: Double,
    val pe: Double,
    val earningsAnnouncement: String,
    val sharesOutstanding: Long,
    val timestamp: Long
)