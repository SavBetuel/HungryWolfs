package com.example.hungrywolfs.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.hungrywolfs.R
import com.example.hungrywolfs.model.OverviewViewModel
import com.example.hungrywolfs.adapters.CategoriesAdapter
import com.example.hungrywolfs.adapters.HomeFoodAdapter
import com.example.hungrywolfs.databinding.FragmentHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeFragment : Fragment() {

    private val viewModel: OverviewViewModel by activityViewModels()
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

        divider(requireContext(), binding.categoriesRecyclerView)
        divider(requireContext(), binding.homeFoodRecyclerView)

        binding.categoriesRecyclerView.adapter = categoriesAdapter
        binding.homeFoodRecyclerView.adapter = homeFoodAdapter

        viewModel.foodCategories.observe(viewLifecycleOwner) { categoriesAdapter.setData(it.categories) }
        viewModel.foodHomeFragment.observe(viewLifecycleOwner) {
            homeFoodAdapter.setData(it.meals)
            binding.homeFoodRecyclerView.scrollToPosition(0)
        }
    }

    fun searchFood() {
        findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
    }

    private fun divider(context: Context, recyclerView: RecyclerView) {
        val divider = DividerItemDecoration(context, RecyclerView.HORIZONTAL)
        ContextCompat.getDrawable(requireContext(), R.drawable.divider)?.let { divider.setDrawable(it) }
        recyclerView.addItemDecoration(divider)
    }
}