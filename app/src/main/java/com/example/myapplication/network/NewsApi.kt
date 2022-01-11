package com.example.myapplication.network

import com.example.myapplication.model.ResponseNews
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("/v2/everything")
    fun getListNews(@Query("q") q: String,
                    @Query("apiKey") apiKey: String,
                    @Query("pageSize") pageSize: Int,
                    @Query("page") page: Int): Call<ResponseNews>
}