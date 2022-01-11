package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.network.NewsApi
import com.example.myapplication.model.Article
import com.example.myapplication.model.ResponseNews
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsViewModel: ViewModel() {
    val articles = MutableLiveData<List<Article>>()
    val isLoading = MutableLiveData<Boolean>()
    val errMessage = MutableLiveData<Throwable>()

    init {
        callApi()
    }

    fun callApi(){
        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiService = retrofit.create(NewsApi::class.java)

        val news = apiService.getListNews("bitcoin","eaf0ed5151ec425098796b4b0e862245", 10, 1)

        isLoading.value = true
        news.enqueue(object: Callback<ResponseNews> {
            override fun onResponse(call: Call<ResponseNews>, response: Response<ResponseNews>) {
                if (response.isSuccessful){
                    isLoading.value = false
                    articles.value = response.body()!!.articles
                }
            }

            override fun onFailure(call: Call<ResponseNews>, t: Throwable) {
                isLoading.value = false
                errMessage.value = t
            }
        })
    }

}