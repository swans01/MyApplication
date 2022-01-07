package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //internal lateinit var button: Button
    //internal lateinit var textBI: TextView
    internal lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        //textBI = findViewById(R.id.textView)

        //button = findViewById(R.id.button)

        binding.button.setOnClickListener { view ->
            val moveIntent = Intent(this@MainActivity, NewsRecyclerViewActivity::class.java)
            moveIntent.putExtra("textSent", binding.textView.text)
            startActivity(moveIntent)
        }

    }
}