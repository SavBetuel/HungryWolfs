package com.example.hungrywolfs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.hungrywolfs.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private val viewModel : OverviewViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentHomeBinding.inflate(inflater)

        binding.lifecycleOwner=this
        binding.viewModel = viewModel
        binding.categoriesRecyclerView.adapter= CategoriesAdapter()

        return binding.root
    }


}