package com.example.myapplication.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.viewmodel.NewsDetailViewModel
import com.example.myapplication.viewmodel.NewsViewModel
import com.example.myapplication.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Named

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    abstract fun bindViewModel(newsViewModel: NewsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NewsDetailViewModel::class)
    abstract fun bindNewsDetailViewModel(newsDetailViewModel: NewsDetailViewModel): ViewModel

//    companion object{
//        @Provides
//        fun provideString(): String{
//            return "news"
//        }
//    }

}