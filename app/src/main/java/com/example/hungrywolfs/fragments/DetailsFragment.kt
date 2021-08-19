package com.example.hungrywolfs.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.hungrywolfs.R
import com.example.hungrywolfs.databinding.FragmentDetailsBinding
import com.example.hungrywolfs.model.DetailsViewModel


class DetailsFragment : Fragment() {

    companion object{
        val ID_MEAL = "idMeal"
    }

    private lateinit var binding: FragmentDetailsBinding
    private lateinit var idMeal: String
    private val viewModel: DetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idMeal = it.getString(ID_MEAL).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        Log.d("DEB_details", "idMeal from detailsFragment is: $idMeal")
        viewModel.getDetails(idMeal)
        viewModel.setDetailsScreen(binding)



    }
}