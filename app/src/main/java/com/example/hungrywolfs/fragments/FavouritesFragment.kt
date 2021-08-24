package com.example.hungrywolfs.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hungrywolfs.adapters.FavouritesAdapter
import com.example.hungrywolfs.databinding.FragmentFavouritesBinding
import com.example.hungrywolfs.model.FavouritesViewModel


class FavouritesFragment : Fragment() {

    private lateinit var binding: FragmentFavouritesBinding
    private val viewModel: FavouritesViewModel by viewModels()
    private val favouritesAdapter = FavouritesAdapter{ idMeal -> if (idMeal != null) { navigateToDetails(idMeal) } }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavouritesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerFavourites.adapter = favouritesAdapter
        viewModel.userFavouritesFood.observe(viewLifecycleOwner){
            favouritesAdapter.setData(it)
        }
    }

    private fun navigateToDetails(idMeal: String){
        val action = FavouritesFragmentDirections.actionFavouritesFragmentToDetailsFragment(idMeal = idMeal)
        findNavController().navigate(action)
    }
}