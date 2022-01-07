package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.myapplication.databinding.ActivityUserDetailBinding

class UserDetailActivity : AppCompatActivity() {
//    internal lateinit var name: TextView
//    internal lateinit var alias: TextView
//    internal lateinit var affiliation: TextView
//    internal lateinit var photo: ImageView
//    internal lateinit var description: TextView

    private lateinit var binding: ActivityUserDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nameDetail.text = intent.getStringExtra("name")
        binding.aliasDetail.text = intent.getStringExtra("alias")
        binding.affiliationDetail.text = intent.getStringExtra("affiliation")
        binding.descriptionDetail.text = intent.getStringExtra("description")
        binding.photoDetail.setImageResource(intent.getIntExtra("photo", 0))

//        name = findViewById(R.id.nameDetail)
//        alias = findViewById(R.id.aliasDetail)
//        affiliation = findViewById(R.id.affiliationDetail)
//        photo = findViewById(R.id.photoDetail)
//        description = findViewById(R.id.descriptionDetail)
//
//        name.text = intent.getStringExtra("name")
//        alias.text = intent.getStringExtra("alias")
//        affiliation.text = intent.getStringExtra("affiliation")
//        description.text = intent.getStringExtra("description")
//        photo.setImageResource(intent.getIntExtra("photo", 0))
    }
}