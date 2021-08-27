package com.example.hungrywolfs.fragments

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.hungrywolfs.R
import com.example.hungrywolfs.adapters.SearchFoodAdapter
import com.example.hungrywolfs.databinding.FragmentSearchBinding
import com.example.hungrywolfs.model.SearchViewModel

class SearchFragment : Fragment(), SearchView.OnQueryTextListener {

    private val viewModel: SearchViewModel by viewModels()
    private lateinit var binding: FragmentSearchBinding
    private val searchFoodAdapter = SearchFoodAdapter{idMeal -> navigateToDetails(idMeal) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSearchBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        binding.viewModel=viewModel
        setupObservers()
        binding.searchBar.setOnQueryTextListener(this)
    }

    override fun onResume() {
        super.onResume()
        showKeyboard()
    }

    private fun navigateToDetails(idMeal: String){
        val action = SearchFragmentDirections.actionSearchFragmentToDetailsFragment(idMeal = idMeal)
        findNavController().navigate(action)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        hideKeyboard()
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        viewModel.getSearchFood(newText)
        return true
    }

    private fun hideKeyboard() {
        val inputMethodManager =
            context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(binding.searchBar.windowToken, 0)
        binding.searchBar.clearFocus()
    }

    private fun showKeyboard() {
        val inputMethodManager =
            requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(binding.searchBar.rootView, InputMethodManager.SHOW_FORCED)
        binding.searchBar.onActionViewExpanded()
    }

    private fun setupRecyclerView () {
        val dividerVertical = DividerItemDecoration(context, RecyclerView.VERTICAL)
        ContextCompat.getDrawable(requireContext(), R.drawable.divider)
            ?.let { dividerVertical.setDrawable(it) }
        binding.searchRecyclerView.addItemDecoration(dividerVertical)

        val dividerHorizontal = DividerItemDecoration(context, RecyclerView.HORIZONTAL)
        ContextCompat.getDrawable(requireContext(), R.drawable.divider)
            ?.let { dividerHorizontal.setDrawable(it) }
        binding.searchRecyclerView.addItemDecoration(dividerHorizontal)

        binding.searchRecyclerView.adapter = searchFoodAdapter
    }

    private fun setupObservers(){
        viewModel.foodSearch.observe(viewLifecycleOwner) {
            searchFoodAdapter.setData(it.meals)
        }

        viewModel.navigateHome.observe(viewLifecycleOwner){
            hideKeyboard()
            findNavController().popBackStack()
        }

        viewModel.searchFoundResults.observe(viewLifecycleOwner) {
            getResultText(it)
        }

        viewModel.successfullyApiCall.observe(viewLifecycleOwner){
            switchViews(it)
        }
    }

    private fun switchViews(successCheck: Boolean) {
        if(successCheck) {
            binding.bigCardView.visibility = View.VISIBLE
            binding.constraintNoItemFound.visibility = View.INVISIBLE
        }else {
            binding.bigCardView.visibility = View.INVISIBLE
            binding.constraintNoItemFound.visibility = View.VISIBLE
        }
    }

    private fun getResultText(newResults: Int) {
        val showText = getString(R.string.found_search_results,
                resources.getQuantityString(R.plurals.choose_result, newResults, newResults))
        binding.countText.text = showText
    }
}
