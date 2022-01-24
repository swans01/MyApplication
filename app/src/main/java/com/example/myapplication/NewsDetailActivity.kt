package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.ActivityNewsDetailBinding
import com.example.myapplication.viewmodel.NewsDetailViewModel
import javax.inject.Inject

class NewsDetailActivity : AppCompatActivity() {
    private lateinit var viewModel: NewsDetailViewModel
    private lateinit var binding: ActivityNewsDetailBinding

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //viewModel = ViewModelProvider(this)[NewsDetailViewModel::class.java]
        DaggerAppComponent.builder().build().injectDetail(this)
        viewModel = ViewModelProvider(this, factory)[NewsDetailViewModel::class.java]
        viewModel.setDetail()

        viewModel.title.observe(this, {title ->
            binding.nameDetail.text = title
        })
    }
}