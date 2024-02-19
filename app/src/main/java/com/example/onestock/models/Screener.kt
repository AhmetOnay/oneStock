package com.example.onestock.models

data class Screener(
    val symbol: String,
    val companyName: String,
    val marketCap: Long,
    val sector: String,
    val industry: String,
    val beta: Double,
    val price: Double,
    val lastAnnualDividend: Double,
    val volume: Long,
    val exchange: String,
    val exchangeShortName: String,
    val country: String,
    val isEtf: Boolean,
    val isActivelyTrading: Boolean
)
