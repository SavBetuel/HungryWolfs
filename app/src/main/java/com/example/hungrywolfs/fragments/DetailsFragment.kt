package com.example.hungrywolfs.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.hungrywolfs.R
import com.example.hungrywolfs.adapters.TagsAdapter
import com.example.hungrywolfs.databinding.FragmentDetailsBinding
import com.example.hungrywolfs.model.DetailsViewModel

class DetailsFragment : Fragment() {

    companion object {
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
        binding.constraintLayoutDetails.visibility = View.GONE
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        viewModel.getDetails(idMeal)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.foodDetails.observe(viewLifecycleOwner) {
            binding.constraintLayoutDetails.visibility = View.VISIBLE
        }

        viewModel.listOfTags.observe(viewLifecycleOwner) {
            binding.detailsRecyclerView.visibility = View.VISIBLE
            tagsAdapter.setTags(it)
        }

        viewModel.navigateBack.observe(viewLifecycleOwner) {
            findNavController().popBackStack()
        }
    }

    private fun setupRecyclerView() {
        val divider = DividerItemDecoration(context, RecyclerView.HORIZONTAL)
        ContextCompat.getDrawable(requireContext(), R.drawable.divider_small)
            ?.let { divider.setDrawable(it) }

        binding.detailsRecyclerView.addItemDecoration(divider)
        binding.detailsRecyclerView.adapter = tagsAdapter
    }
}