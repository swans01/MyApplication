package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
            val moveIntent = Intent(this@MainActivity, ReplicateActivity::class.java)
            startActivity(moveIntent)
        }

        binding.button2.setOnClickListener { view ->
            val moveIntent = Intent(this@MainActivity, RecyclerViewActivity::class.java)
            startActivity(moveIntent)
        }

        binding.button3.setOnClickListener { view ->
            val moveIntent = Intent(this@MainActivity, NewsRecyclerViewActivity::class.java)
            moveIntent.putExtra("key", "a7551c3980934b3d9f122dd4baef482e")
            startActivity(moveIntent)
        }

//        binding.button.setOnClickListener { view ->
//            val moveIntent = Intent(this@MainActivity, NewsRecyclerViewActivity::class.java)
//            moveIntent.putExtra("key", "a7551c3980934b3d9f122dd4baef482e")
//            moveIntent.putExtra("textSent", binding.textView.text)
//            startActivity(moveIntent)
//        }

//        binding.button2.setOnClickListener { view ->
//            val moveIntent = Intent(this@MainActivity, NewsRecyclerViewActivity::class.java)
//            moveIntent.putExtra("key", "wrong key")
//            startActivity(moveIntent)
//        }

    }
}