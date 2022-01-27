package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.myapplication.adapter.ListNewsAdapter
import com.example.myapplication.databinding.FragmentNewsRecyclerViewBinding
import com.example.myapplication.model.Article
import com.example.myapplication.viewmodel.NewsViewModel
import javax.inject.Inject

class NewsRecyclerViewFragment : Fragment() {
    private lateinit var binding: FragmentNewsRecyclerViewBinding
    private lateinit var listNewsAdapter: ListNewsAdapter
    private lateinit var viewModel: NewsViewModel
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
     private val args: NewsRecyclerViewFragmentArgs by navArgs()

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewsRecyclerViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val key = "a7551c3980934b3d9f122dd4baef482e"
        val newsQuery = args.newsQuery
        var page = 1
        var layoutManager = LinearLayoutManager(view.context)

//        val navActivity = activity as NavActivity
//        navActivity.actionBar?.setDisplayHomeAsUpEnabled(true)
//        navActivity.actionBar?.setDisplayShowTitleEnabled(true)
//        navActivity.actionBar?.setTitle("News")
//        navActivity.actionBar.elevation = 0f

        binding.recyclerViewNews.layoutManager = layoutManager


        swipeRefreshLayout = binding.refreshLayout
        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            page = 1
            listNewsAdapter.clear()
            viewModel.callApi(key, newsQuery, page)
        }

        binding.recyclerViewNews.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (layoutManager.itemCount != 0 && layoutManager.findLastCompletelyVisibleItemPosition() == layoutManager.itemCount - 1){
                    page++
                    Log.d("page", "$page")
                    viewModel.callApi(key, newsQuery, page)
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })


        DaggerAppComponent.builder().build().injectFragment(this)
        viewModel = ViewModelProvider(this, factory)[NewsViewModel::class.java]
        viewModel.callApi(key, newsQuery, page)
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
            Toast.makeText(view.context, "$errCode", Toast.LENGTH_LONG).show()
        })

        viewModel.errMessage.observe(this, {errMessage ->
            if (errMessage == "Network Error"){
                binding.noInternet.visibility = View.VISIBLE
            }
            Toast.makeText(view.context, "$errMessage", Toast.LENGTH_LONG).show()
        })



    }

    private fun loadNewsToActivity(){
        listNewsAdapter = ListNewsAdapter()
        binding.recyclerViewNews.adapter = listNewsAdapter
        listNewsAdapter.setOnItemClickCallback(object : ListNewsAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Article) {
//                val moveIntent = Intent(this@NewsRecyclerViewActivity, NewsDetailActivity::class.java)
//                moveIntent.putExtra("title", data.title)
//                val openUrl = Intent(Intent.ACTION_VIEW, Uri.parse(data.url))
//                startActivity(openUrl)
                //findNavController().navigate(R.id.action_newsRecyclerViewFragment_to_userDetailFragment)
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