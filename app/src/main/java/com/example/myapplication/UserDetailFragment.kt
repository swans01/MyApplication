package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.FragmentUserDetailBinding

class UserDetailFragment : Fragment() {
    lateinit var binding: FragmentUserDetailBinding
    private val args: UserDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.nameDetail.text = args.name
        binding.aliasDetail.text = args.alias
        binding.affiliationDetail.text = args.affiliation
        binding.descriptionDetail.text = args.description
        binding.photoDetail.setImageResource(args.photo)
    }

}