package com.example.myapplication

import com.example.myapplication.di.ViewModelModule
import dagger.Component

@Component(modules = [ViewModelModule::class])
interface AppComponent {
    fun inject(newsRecyclerViewActivity: NewsRecyclerViewActivity)

    fun injectFragment(newsRecyclerViewFragment: NewsRecyclerViewFragment)

    fun injectDetail(newsDetailActivity: NewsDetailActivity)
}


//@Module
//abstract class AppModule{
//    @Binds
//    abstract fun bindsViewModelFactory(factory: Factory): ViewModelProvider.Factory
//}