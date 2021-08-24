package com.example.hungrywolfs.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.hungrywolfs.R
import com.example.hungrywolfs.adapters.CategoriesAdapter
import com.example.hungrywolfs.adapters.FavouritesAdapter
import com.example.hungrywolfs.adapters.HomeFoodAdapter
import com.example.hungrywolfs.databinding.FragmentFavouritesBinding
import com.example.hungrywolfs.model.FavouritesViewModel


class FavouritesFragment : Fragment() {

    private lateinit var binding: FragmentFavouritesBinding
    private val viewModel: FavouritesViewModel by viewModels()
    private val favouritesAdapter = FavouritesAdapter(
        { idMeal -> if (idMeal != null) { navigateToDetails(idMeal) } },
        { position -> viewModel.removeItem(position) })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavouritesBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObservers()
    }

    private fun navigateToDetails(idMeal: String) {
        val action = FavouritesFragmentDirections.actionFavouritesFragmentToDetailsFragment(idMeal = idMeal)
        findNavController().navigate(action)
    }

    private fun setupRecyclerView() {
        val dividerVertical = DividerItemDecoration(context, RecyclerView.VERTICAL)
        ContextCompat.getDrawable(requireContext(), R.drawable.divider_medium)
            ?.let { dividerVertical.setDrawable(it) }

        binding.recyclerFavourites.addItemDecoration(dividerVertical)
        binding.recyclerFavourites.adapter = favouritesAdapter
    }

    private fun setupObservers() {
        viewModel.userFavouritesFood.observe(viewLifecycleOwner) {
            favouritesAdapter.setData(it)
        }
    }
}