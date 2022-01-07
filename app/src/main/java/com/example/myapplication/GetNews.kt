package com.example.myapplication

import com.example.myapplication.model.ResponseNews
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GetNews {
    @GET("/v2/top-headlines")
    fun getListNews(@Query("country") country: String, @Query("apiKey") apiKey: String): Call<ResponseNews>
}