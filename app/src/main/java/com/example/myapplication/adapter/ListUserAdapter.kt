package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.UserData
import com.example.myapplication.databinding.ItemUserBinding

class ListUserAdapter(private val listUser: UserData): RecyclerView.Adapter<ListUserAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: ListUserAdapter.OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: ListUserAdapter.OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        //val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.binding.name.text = UserData.listName[position]
        holder.binding.alias.text = UserData.listAlias[position]
        holder.binding.affiliation.text = UserData.listAffiliation[position]
        holder.binding.photo.setImageResource(UserData.listPhoto[position])
        holder.binding.root.setOnClickListener {
            onItemClickCallback.onItemClicked(UserData, position)
        }
    }

    override fun getItemCount(): Int {
        return UserData.listName.size
    }

    class ListViewHolder(var binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: UserData, position: Int)
    }
}