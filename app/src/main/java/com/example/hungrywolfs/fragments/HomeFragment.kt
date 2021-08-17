package com.example.hungrywolfs.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.core.content.ContextCompat
import androidx.core.view.ScrollingView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.hungrywolfs.R
import com.example.hungrywolfs.model.OverviewViewModel
import com.example.hungrywolfs.adapters.CategoriesAdapter
import com.example.hungrywolfs.adapters.HomeFoodAdapter
import com.example.hungrywolfs.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private val viewModel: OverviewViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater)
        binding.homeFragment = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoriesAdapter = CategoriesAdapter { category -> viewModel.getSelectedFoodHome(category) }
        val homeFoodAdapter = HomeFoodAdapter()

        setupRecyclerView(categoriesAdapter, homeFoodAdapter)

        viewModel.foodCategories.observe(viewLifecycleOwner) { categoriesAdapter.setData(it.categories) }
        viewModel.foodItems.observe(viewLifecycleOwner) {
            homeFoodAdapter.setData(it.meals)
            binding.homeFoodRecyclerView.scrollToPosition(0)
        }
    }

    fun searchFood() {
        findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
    }

    private fun setupRecyclerView(categoriesAdapter : CategoriesAdapter,homeFoodAdapter: HomeFoodAdapter) {
        val divider = DividerItemDecoration(context, RecyclerView.HORIZONTAL)
        ContextCompat.getDrawable(requireContext(), R.drawable.divider)?.let { divider.setDrawable(it) }

        binding.categoriesRecyclerView.addItemDecoration(divider)
        binding.homeFoodRecyclerView.addItemDecoration(divider)

        binding.categoriesRecyclerView.adapter = categoriesAdapter
        binding.homeFoodRecyclerView.adapter = homeFoodAdapter

    }
}