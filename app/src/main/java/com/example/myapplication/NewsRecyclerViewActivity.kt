package com.example.myapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityNewsRecyclerViewBinding
import com.example.myapplication.model.Article
import com.example.myapplication.model.ResponseNews
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsRecyclerViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsRecyclerViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerViewNews.layoutManager = LinearLayoutManager(this)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiService = retrofit.create(GetNews::class.java)

        val news = apiService.getListNews("id","eaf0ed5151ec425098796b4b0e862245")

        news.enqueue(object: Callback<ResponseNews> {
            override fun onResponse(call: Call<ResponseNews>, response: Response<ResponseNews>) {
                if (response.isSuccessful){
                    val listNewsAdapter = ListNewsAdapter(response.body()!!.articles)
                    binding.recyclerViewNews.adapter = listNewsAdapter

                    listNewsAdapter.setOnItemClickCallback(object : ListNewsAdapter.OnItemClickCallback{
                        override fun onItemClicked(data: Article) {
                            val moveIntent = Intent(Intent.ACTION_VIEW, Uri.parse(data.url))
                            startActivity(moveIntent)
                        }
                    })
                }
            }

            override fun onFailure(call: Call<ResponseNews>, t: Throwable) {
                Toast.makeText(this@NewsRecyclerViewActivity, "${t.message}", Toast.LENGTH_LONG).show()
            }
        })


    }
}