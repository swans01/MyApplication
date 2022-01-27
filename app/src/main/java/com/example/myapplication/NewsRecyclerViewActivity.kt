package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.myapplication.adapter.ListNewsAdapter
import com.example.myapplication.databinding.ActivityNewsRecyclerViewBinding
import com.example.myapplication.model.Article
import com.example.myapplication.viewmodel.NewsViewModel
import javax.inject.Inject

class NewsRecyclerViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsRecyclerViewBinding
    private lateinit var listNewsAdapter: ListNewsAdapter
    private lateinit var viewModel: NewsViewModel
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private var layoutManager = LinearLayoutManager(this)

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        val key = intent.getStringExtra("key")
        var page = 1

        super.onCreate(savedInstanceState)
        binding = ActivityNewsRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerViewNews.layoutManager = layoutManager


        swipeRefreshLayout = binding.refreshLayout
        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            page = 1
            listNewsAdapter.clear()
           // viewModel.callApi(key, page)
        }

        binding.recyclerViewNews.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (layoutManager.itemCount != 0 && layoutManager.findLastCompletelyVisibleItemPosition() == layoutManager.itemCount - 1){
                    page++
                    Log.d("page", "$page")
                   // viewModel.callApi(key!!, page)
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })


        //viewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        //viewModel = ViewModelProvider(this, Factory(RetroInstance.getRetroInstance().create(NewsApi::class.java)))[NewsViewModel::class.java]
        DaggerAppComponent.builder().build().inject(this)
        viewModel = ViewModelProvider(this, factory)[NewsViewModel::class.java]
        //viewModel.callApi(key!!, page)
        loadNewsToActivity()
        
        viewModel.articles.observe(this, {articles ->
            listNewsAdapter.addList(articles)
        })

        viewModel.isLoading.observe(this, {isLoading ->
            showLoading(isLoading)
        })

        viewModel.errCode.observe(this, {errCode ->
            if (errCode == "401"){
                binding.noAccess.visibility = View.VISIBLE
            }
            Toast.makeText(this@NewsRecyclerViewActivity, "$errCode", Toast.LENGTH_LONG).show()
        })

        viewModel.errMessage.observe(this, {errMessage ->
            if (errMessage == "Network Error"){
                binding.noInternet.visibility = View.VISIBLE
            }
            Toast.makeText(this@NewsRecyclerViewActivity, "$errMessage", Toast.LENGTH_LONG).show()
        })


    }


    private fun loadNewsToActivity(){
        listNewsAdapter = ListNewsAdapter()
        binding.recyclerViewNews.adapter = listNewsAdapter
        listNewsAdapter.setOnItemClickCallback(object : ListNewsAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Article) {
                val moveIntent = Intent(this@NewsRecyclerViewActivity, NewsDetailActivity::class.java)
                moveIntent.putExtra("title", data.title)
                //val moveIntent = Intent(Intent.ACTION_VIEW, Uri.parse(data.url))
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