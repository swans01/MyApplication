package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.myapplication.databinding.FragmentNewsSearchBinding

class NewsSearchFragment : Fragment() {
    lateinit var binding: FragmentNewsSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewsSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val newsQuery = binding.search.text
        binding.buttonSearch.setOnClickListener {
            if (newsQuery.isEmpty()){
                binding.search.error = "Please input your search query"
            }else{
                val direction = NewsSearchFragmentDirections.actionNewsSearchFragmentToNewsRecyclerViewFragment(newsQuery.toString())
                it.findNavController().navigate(direction)
            }
        }
    }

}