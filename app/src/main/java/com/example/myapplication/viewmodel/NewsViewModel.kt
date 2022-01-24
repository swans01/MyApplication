package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.Article
import com.example.myapplication.network.RetroInstance
import kotlinx.coroutines.*
import java.io.IOException
import javax.inject.Inject
import javax.inject.Provider

class NewsViewModel @Inject constructor(private val api: RetroInstance): ViewModel() {

    val articles = MutableLiveData<List<Article>>()
    val isLoading = MutableLiveData<Boolean>()
    val errMessage = MutableLiveData<String>()
    val errCode = MutableLiveData<String>()

    fun callApi(key: String, page: Int){
        //val apiService = RetroInstance.getRetroInstance().create(NewsApi::class.java)

        isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val responseNews = api.getRetroInstance().getListNews("metaverse", key, 10, page)
                if (responseNews.code() == 200){
                    isLoading.postValue(false)
                    articles.postValue(responseNews.body()!!.articles)
                }else if (responseNews.code() == 401){
                    isLoading.postValue(false)
                    errCode.postValue("401")
                }
            }
            catch (e: Throwable){
                if (e is IOException){
                    errMessage.postValue("Network Error")
                }
            }

        }

    }

}

//class Factory @Inject constructor (private val newsViewModelProvider: Provider<NewsViewModel>): ViewModelProvider.Factory{
//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return  newsViewModelProvider.get() as T
//    }
//}

//class NewsViewModelFactory(private val apiService: ApiEndpoint): ViewModelProvider.Factory{
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
//            return NewsViewModel(apiService) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}