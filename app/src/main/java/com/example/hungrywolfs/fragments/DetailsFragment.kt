package com.example.hungrywolfs.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.hungrywolfs.R
import com.example.hungrywolfs.adapters.TagsAdapter
import com.example.hungrywolfs.databinding.FragmentDetailsBinding
import com.example.hungrywolfs.model.DetailsViewModel

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: DetailsViewModel by viewModels()
    private val tagsAdapter = TagsAdapter()
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        viewModel.getDetails(args.idMeal)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.buttonStatus.observe(viewLifecycleOwner) {
            viewModel.addItemFavourites(binding.favouritesButton.isChecked, viewModel.foodDetails.value)
        }

        viewModel.foodDetails.observe(viewLifecycleOwner) {
            binding.favouritesButton.isChecked = viewModel.isSelected(viewModel.foodDetails.value)
            binding.constraintLayoutDetails.visibility = View.VISIBLE
        }

        viewModel.listOfTags.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                binding.detailsRecyclerView.visibility = View.VISIBLE
                tagsAdapter.setTags(it)
            }
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