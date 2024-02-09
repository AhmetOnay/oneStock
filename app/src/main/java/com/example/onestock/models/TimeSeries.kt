package com.example.onestock.models

data class TimeSeries(
    val meta: MetaData,
    val values: List<StockValue>,
    val status: String
)

data class MetaData(
    val symbol: String,
    val interval: String,
    val currency: String,
    val exchange_timezone: String,
    val exchange: String,
    val mic_code: String,
    val type: String
)

data class StockValue(
    val datetime: String,
    val open: String,
    val high: String,
    val low: String,
    val close: String,
    val volume: String
)
