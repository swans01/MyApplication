package com.example.myapplication.model

import android.net.Uri
import android.text.format.DateFormat
import android.text.format.DateUtils
import retrofit2.http.Url
import java.net.URI
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

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
    val id: Int?,
    val name: String
)
