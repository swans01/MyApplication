package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.Activity2Binding

class Activity2 : AppCompatActivity() {

    //internal lateinit var textReceived: TextView
    private lateinit var binding: Activity2Binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        //textReceived = findViewById(R.id.textReceived)

        val text = intent.getStringExtra("textSent")
        binding.textReceived.text = text
        //textReceived.text = text

        supportFragmentManager.beginTransaction().add(R.id.fragmentPlaceholder, NewFragment()).commit()

        var actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowTitleEnabled(false)
        actionBar?.elevation = 0f

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}