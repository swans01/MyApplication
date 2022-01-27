package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment

class NavActivity : AppCompatActivity() {

//    lateinit var actionBar: ActionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav)

//        actionBar = supportActionBar!!
//        actionBar?.setDisplayHomeAsUpEnabled(true)
//        actionBar?.setDisplayShowTitleEnabled(true)
//        actionBar?.setTitle("News")
//        actionBar.elevation = 0f

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
    }

    override fun onSupportNavigateUp(): Boolean {
        return this.findNavController(R.id.nav_host_fragment).navigateUp()
    }
}