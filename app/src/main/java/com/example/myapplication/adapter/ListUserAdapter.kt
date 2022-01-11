package com.example.myapplication.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.UserData
import com.example.myapplication.UserDetailActivity
import com.example.myapplication.databinding.ItemUserBinding

class ListUserAdapter(private val listUser: UserData): RecyclerView.Adapter<ListUserAdapter.ListViewHolder>() {

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
            val moveIntent = Intent(it.context, UserDetailActivity::class.java)
            moveIntent.putExtra("name", UserData.listName[position])
            moveIntent.putExtra("alias", UserData.listAlias[position])
            moveIntent.putExtra("affiliation", UserData.listAffiliation[position])
            moveIntent.putExtra("photo", UserData.listPhoto[position])
            moveIntent.putExtra("description", UserData.listDescription[position])
            startActivity(it.context, moveIntent, null)

        }
//        holder.name.text = listUser.listName[position]
//        holder.alias.text = "Current Alias: ${listUser.listAlias[position]}"
//        holder.affiliation.text = "Affiliation: ${listUser.listAffiliation[position]}"
//        holder.photo.setImageResource(listUser.listPhoto[position])
//        holder.itemView.setOnClickListener {
//            val moveIntent = Intent(it.context, UserDetailActivity::class.java)
//            moveIntent.putExtra("name", holder.name.text)
//            moveIntent.putExtra("alias", holder.alias.text)
//            moveIntent.putExtra("affiliation", holder.affiliation.text)
//            moveIntent.putExtra("photo", listUser.listPhoto[position])
//            moveIntent.putExtra("description", listUser.listDescription[position])
//            startActivity(it.context, moveIntent, null)
//        }
    }

    override fun getItemCount(): Int {
        return UserData.listName.size
    }

    class ListViewHolder(var binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
//        var name: TextView = itemView.findViewById(R.id.name)
//        var photo: ImageView = itemView.findViewById(R.id.photo)
//        var alias: TextView = itemView.findViewById(R.id.alias)
//        var affiliation: TextView = itemView.findViewById((R.id.affiliation))
    }
}