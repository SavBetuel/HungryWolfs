package com.example.hungrywolfs.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.hungrywolfs.OverviewViewModel
import com.example.hungrywolfs.adapters.CategoriesAdapter
import com.example.hungrywolfs.adapters.HomeFoodAdapter
import com.example.hungrywolfs.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private val viewModel : OverviewViewModel by activityViewModels()
    private lateinit var binding: FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val categoriesAdapter = CategoriesAdapter()
        val homeFoodAdapter = HomeFoodAdapter()

        binding.categoriesRecyclerView.adapter= categoriesAdapter
        //binding.homeFoodRecyclerView.adapter = homeFoodAdapter

        viewModel.foodCategories.observe(viewLifecycleOwner) { categoriesAdapter.setData(it.categories)}
    }



}