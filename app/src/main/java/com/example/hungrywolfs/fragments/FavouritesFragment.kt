package com.example.hungrywolfs.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.hungrywolfs.adapters.FavouritesAdapter
import com.example.hungrywolfs.databinding.FragmentFavouritesBinding
import com.example.hungrywolfs.model.SharedViewModel

class FavouritesFragment : Fragment() {

    private lateinit var binding: FragmentFavouritesBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavouritesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val favouritesAdapter = FavouritesAdapter(sharedViewModel.userFavouritesFood){idMeal ->
            if (idMeal != null) {
                navigateToDetails(idMeal)
            }
        }
        binding.recyclerFavourites.adapter = favouritesAdapter
    }

    private fun navigateToDetails(idMeal: String){
        val action = FavouritesFragmentDirections.actionFavouritesFragmentToDetailsFragment(idMeal = idMeal)
        findNavController().navigate(action)
    }
}