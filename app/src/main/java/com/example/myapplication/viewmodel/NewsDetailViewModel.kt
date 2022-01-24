package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject
import javax.inject.Named

class NewsDetailViewModel @Inject constructor() : ViewModel() {

    val title = MutableLiveData<String>()

    fun setDetail(){
        title.value = "news"
    }

}