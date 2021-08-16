package com.example.hungrywolfs.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.graphics.toColor
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.hungrywolfs.R
import com.example.hungrywolfs.adapters.SearchFoodAdapter
import com.example.hungrywolfs.databinding.FragmentSearchBinding
import com.example.hungrywolfs.model.OverviewViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class SearchFragment : Fragment(), SearchView.OnQueryTextListener {

    private val viewModel: OverviewViewModel by activityViewModels()
    private lateinit var binding: FragmentSearchBinding
    val searchFoodAdapter = SearchFoodAdapter()
    var foundResults: Int = 0


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

        binding.searchFragment = this
        binding.viewModel = viewModel
        binding.lifecycleOwner=viewLifecycleOwner

        viewModel.getSearchFood("Be")

        binding.searchRecyclerView.adapter = searchFoodAdapter
        viewModel.foodSearch.observe(viewLifecycleOwner) {
            searchFoodAdapter.setData(it.meals)
            foundResults = viewModel.foodSearch.value!!.meals.size
        }
        binding.searchBar.setOnQueryTextListener(this)
    }

    override fun onResume() {
        super.onResume()
        showKeyboard()
        divider(requireContext(), binding.searchRecyclerView)
        Log.d("test", "onResume called")
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

    private fun showKeyboard() {
        val inputMethodManager = requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(binding.searchBar.rootView, InputMethodManager.SHOW_FORCED)
    }

    private fun divider(context: Context, recyclerView: RecyclerView) {
        val dividerVertical = DividerItemDecoration(context, RecyclerView.VERTICAL)
        ContextCompat.getDrawable(requireContext(), R.drawable.divider)?.let { dividerVertical.setDrawable(it) }
        recyclerView.addItemDecoration(dividerVertical)

    }

    fun backToHomeFragment() {
        Log.d("test","back pressed")
        findNavController().navigate(R.id.action_searchFragment_to_homeFragment)
    }
}
