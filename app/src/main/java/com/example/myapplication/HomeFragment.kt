package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.myapplication.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_home, container, false)
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_replicateFragment)
        }

        binding.button2.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_recyclerViewFragment)
        }

        binding.button3.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_newsRecyclerViewFragment)
        }
    }

}