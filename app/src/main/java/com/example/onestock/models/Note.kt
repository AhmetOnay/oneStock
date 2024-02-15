package com.example.onestock.models

import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
data class Note(
    val id: Long = 0,
    val text: String = "",
    val timestamp: Long = 0
)
