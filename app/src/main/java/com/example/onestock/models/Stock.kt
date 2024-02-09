package com.example.onestock.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Stock(
    @PrimaryKey
    val symbol: String,
    val name: String,
    val favourite: Boolean,
    val note: String
    )
