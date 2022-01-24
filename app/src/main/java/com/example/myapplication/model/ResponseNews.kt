package com.example.myapplication.model


data class ResponseNews(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)

data class Article(
    val source: ListSource,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String,
)

data class ListSource(
    val id: String?,
    val name: String
)
