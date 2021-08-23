package com.example.hungrywolfs.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.hungrywolfs.R
import com.example.hungrywolfs.adapters.CategoriesAdapter
import com.example.hungrywolfs.adapters.HomeFoodAdapter
import com.example.hungrywolfs.databinding.FragmentHomeBinding
import com.example.hungrywolfs.model.HomeViewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private val categoriesAdapter = CategoriesAdapter { category -> viewModel.getSelectedFoodHome(category) }
    private val homeFoodAdapter = HomeFoodAdapter{idMeal -> navigateToDetails(idMeal)}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        setupRecyclerView(categoriesAdapter, homeFoodAdapter)
        setupObservers()
    }

    private fun navigateToDetails(idMeal: String){
        val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(idMeal = idMeal)
        findNavController().navigate(action)
    }

    private fun setupRecyclerView(categoriesAdapter: CategoriesAdapter, homeFoodAdapter: HomeFoodAdapter) {
        val divider = DividerItemDecoration(context, RecyclerView.HORIZONTAL)
        ContextCompat.getDrawable(requireContext(), R.drawable.divider)
            ?.let { divider.setDrawable(it) }

        binding.categoriesRecyclerView.addItemDecoration(divider)
        binding.homeFoodRecyclerView.addItemDecoration(divider)

        binding.categoriesRecyclerView.adapter = categoriesAdapter
        binding.homeFoodRecyclerView.adapter = homeFoodAdapter
    }

    private fun setupObservers(){
        viewModel.navigateSearch.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }
        viewModel.foodCategories.observe(viewLifecycleOwner) {
            categoriesAdapter.setData(it.categories)
        }
        viewModel.foodItems.observe(viewLifecycleOwner) {
            homeFoodAdapter.setData(it.meals)
            binding.homeFoodRecyclerView.scrollToPosition(0)
        }
    }
}