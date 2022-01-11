package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ItemNewsBinding
import com.example.myapplication.model.Article

class ListNewsAdapter(private val listNews: List<Article>): RecyclerView.Adapter<ListNewsAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        Glide.with(holder.binding.root)
            .load(listNews[position].urlToImage)
            .into(holder.binding.newsImage)
        holder.binding.newsTitle.text = listNews[position].title
        holder.binding.newsSource.text = listNews[position].source.name
        holder.binding.newsAuthor.text = listNews[position].author
        holder.binding.root.setOnClickListener {
            onItemClickCallback.onItemClicked(listNews[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int {
        return listNews.size
    }

    class ListViewHolder(var binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Article)
    }
}

