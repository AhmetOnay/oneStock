package com.example.onestock.models

data class News(
    val meta: Meta,
    val data: List<NewsArticle>
)

data class Meta(
    val found: Int,
    val returned: Int,
    val limit: Int,
    val page: Int
)

data class NewsArticle(
    val uuid: String,
    val title: String,
    val description: String,
    val keywords: String?,
    val snippet: String,
    val url: String,
    val image_url: String,
    val language: String,
    val published_at: String,
    val source: String,
    val relevance_score: Double?,
    val entities: List<Entity>
)

data class Entity(
    val symbol: String,
    val name: String,
    val exchange: String?,
    val exchange_long: String?,
    val country: String?,
    val type: String,
    val industry: String?,
    val match_score: Double,
    val sentiment_score: Double?,
    val highlights: List<Highlight>
)

data class Highlight(
    val highlight: String,
    val sentiment: Double?,
    val highlighted_in: String
)
