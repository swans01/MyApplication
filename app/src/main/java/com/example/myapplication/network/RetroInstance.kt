package com.example.myapplication.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RetroInstance @Inject constructor() {

//    companion object {
//        private val baseURL = "https://newsapi.org/"
//
//        fun getRetroInstance(): Retrofit {
//            return Retrofit.Builder()
//                .baseUrl(baseURL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//
//        }
//    }

    private val baseUrl = "https://newsapi.org/"

    fun getRetroInstance(): NewsApi{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }
//
//    @Provides
//    fun getRetroService(retrofit: Retrofit): NewsApi{
//        return retrofit.create(NewsApi::class.java)
//    }


}