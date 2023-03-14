package com.example.newsreader.domain.model

data class Article(
    val url: String,
    val author: String?,
    val content: String,
    val description: String,
    val publishedAt: String,
    val title: String,
    val urlToImage: String?
)
