package com.example.myapplication.network

import com.example.myapplication.model.ResponseNews
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NewsApi {
    @GET("/v2/everything")
    suspend fun getListNews(@Query("q") q: String,
                            @Header("x-api-key") apiKey: String,
                            @Query("pageSize") pageSize: Int,
                            @Query("page") page: Int): Response<ResponseNews>
}