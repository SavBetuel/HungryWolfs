package com.example.hungrywolfs.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import com.example.hungrywolfs.R
import com.example.hungrywolfs.adapters.SearchFoodAdapter
import com.example.hungrywolfs.databinding.FragmentSearchBinding
import com.example.hungrywolfs.model.OverviewViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class SearchFragment : Fragment(), SearchView.OnQueryTextListener {

    private val viewModel: OverviewViewModel by activityViewModels()
    private lateinit var binding: FragmentSearchBinding
    val searchFoodAdapter = SearchFoodAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentSearchBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)?.visibility = View.INVISIBLE
        showKeyboard(context)
        viewModel.getSearchFood("Beef")

        binding.searchRecyclerView.adapter = searchFoodAdapter
        viewModel.foodSearch.observe(viewLifecycleOwner) {searchFoodAdapter.setData(it.meals)}
        binding.searchBar?.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        hideKeyboard(context)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        viewModel.getSearchFood(newText)
        return true
    }

    private fun hideKeyboard(context: Context?){
        val inputMethodManager = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(binding.searchBar.windowToken, 0)
        binding.searchBar.clearFocus()
    }

    private fun showKeyboard(context: Context?){
        val inputMethodManager = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(binding.searchBar, 0)
    }
}
