package com.example.myapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.myapplication.adapter.ListNewsAdapter
import com.example.myapplication.databinding.ActivityNewsRecyclerViewBinding
import com.example.myapplication.model.Article
import com.example.myapplication.viewmodel.NewsViewModel

class NewsRecyclerViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsRecyclerViewBinding
    private lateinit var listNewsAdapter: ListNewsAdapter
    private lateinit var viewModel: NewsViewModel
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerViewNews.layoutManager = LinearLayoutManager(this)
        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        swipeRefreshLayout = binding.refreshLayout

        viewModel.articles.observe(this, {articles ->
           loadNewsToActivity(articles)
        })

        viewModel.isLoading.observe(this, {isLoading ->
            showLoading(isLoading)
        })

        viewModel.errMessage.observe(this, {errMessage ->
            Toast.makeText(this@NewsRecyclerViewActivity, "${errMessage.message}", Toast.LENGTH_LONG).show()
        })

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            viewModel.callApi()
        }

    }

    private fun loadNewsToActivity(articles: List<Article>){
        listNewsAdapter = ListNewsAdapter(articles)
        binding.recyclerViewNews.adapter = listNewsAdapter

        listNewsAdapter.setOnItemClickCallback(object : ListNewsAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Article) {
                val moveIntent = Intent(Intent.ACTION_VIEW, Uri.parse(data.url))
                startActivity(moveIntent)
            }
        })
    }

    private fun showLoading(isLoading: Boolean){
        if(isLoading){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
        }
    }

}