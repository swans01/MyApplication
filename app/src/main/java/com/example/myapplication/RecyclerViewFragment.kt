package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adapter.ListUserAdapter
import com.example.myapplication.databinding.FragmentRecyclerViewBinding

class RecyclerViewFragment : Fragment() {
    private lateinit var binding: FragmentRecyclerViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRecyclerViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.layoutManager = LinearLayoutManager(view.context)

        val  listUserAdapter = ListUserAdapter(UserData)
        binding.recyclerView.adapter = listUserAdapter

        listUserAdapter.setOnItemClickCallback(object : ListUserAdapter.OnItemClickCallback{
            override fun onItemClicked(data: UserData, position: Int) {
                val direction = RecyclerViewFragmentDirections.actionRecyclerViewFragmentToUserDetailFragment(data.listName[position],
                    data.listAlias[position], data.listAffiliation[position], data.listDescription[position], data.listPhoto[position])

                findNavController().navigate(direction)
            }
        })

    }
}