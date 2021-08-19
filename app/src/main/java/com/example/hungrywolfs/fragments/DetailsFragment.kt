package com.example.hungrywolfs.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.hungrywolfs.R
import com.example.hungrywolfs.adapters.TagsAdapter
import com.example.hungrywolfs.databinding.FragmentDetailsBinding
import com.example.hungrywolfs.model.DetailsViewModel

class DetailsFragment : Fragment() {

    companion object{
        const val ID_MEAL = "idMeal"
    }

    private lateinit var binding: FragmentDetailsBinding
    private lateinit var idMeal: String
    private val viewModel: DetailsViewModel by viewModels()
    private val tagsAdapter = TagsAdapter()

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

        binding.viewModel = viewModel
        viewModel.getDetails(idMeal)
        setupRecyclerView(tagsAdapter)
        setupObservers()

    }

    private fun setupObservers(){
        viewModel.foodDetails.observe(viewLifecycleOwner){
            tagsAdapter.setData(it.meals.first().strTags)
            setDetailsScreen()
        }

        viewModel.navigateBack.observe(viewLifecycleOwner){
            findNavController().popBackStack()
        }
    }

    private fun setupRecyclerView(tagsAdapter :TagsAdapter){
        val divider = DividerItemDecoration(context, RecyclerView.HORIZONTAL)
        ContextCompat.getDrawable(requireContext(), R.drawable.divider_small)
            ?.let { divider.setDrawable(it) }

        binding.detailsRecyclerView.addItemDecoration(divider)
        binding.detailsRecyclerView.adapter=tagsAdapter
    }

    fun setDetailsScreen() {
        val imgUri = viewModel.foodDetails.value?.meals?.get(0)?.strMealThumb?.toUri()
        binding.foodImage.load(imgUri) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }

        binding.foodTitle.text = viewModel.foodDetails.value?.meals?.first()?.strMeal

        binding.ingredientOne.text =
            viewModel.foodDetails.value?.meals?.first()?.strIngredient1 + " " + viewModel.foodDetails.value?.meals?.first()?.strMeasure1
        binding.ingredientTwo.text =
            viewModel.foodDetails.value?.meals?.first()?.strIngredient2 + " " + viewModel.foodDetails.value?.meals?.first()?.strMeasure2
        binding.ingredientTree.text =
            viewModel.foodDetails.value?.meals?.first()?.strIngredient3 + " " + viewModel.foodDetails.value?.meals?.first()?.strMeasure3

        binding.instruction.text = viewModel.foodDetails.value?.meals?.first()?.strInstructions
    }
}