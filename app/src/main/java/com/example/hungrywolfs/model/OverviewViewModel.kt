package com.example.hungrywolfs.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hungrywolfs.network.*
import kotlinx.coroutines.launch

class OverviewViewModel : ViewModel() {

    private val _foodCategories = MutableLiveData<FoodCategories>()
    val foodCategories: LiveData<FoodCategories> = _foodCategories

    private val _foodItems = MutableLiveData<FoodHomeFragment>()
    val foodItems: LiveData<FoodHomeFragment> = _foodItems

    private val _foodSearch = MutableLiveData<FoodSearch>()
    val foodSearch: LiveData<FoodSearch> = _foodSearch

    private val _searchFoundResults = MutableLiveData(0)
    val searchFoundResults: LiveData<Int> = _searchFoundResults

    private val _goSearch = MutableLiveData<Boolean>(false)
    val goSearch: LiveData<Boolean> = _goSearch

    private val _goHome = MutableLiveData<Boolean>(false)
    val goHome: LiveData<Boolean> = _goHome

    init {
        getCategoriesData()
    }

    fun goSearchFalse() {
        _goSearch.value = false
    }

    fun goSearchTrue() {
        _goSearch.value = true
    }

    fun goHomeFalse() {
        _goHome.value = false
    }

    fun goHomeTrue() {
        _goHome.value = true
    }


    private fun getCategoriesData() {
        viewModelScope.launch {
            try {
                FoodApi.retrofitService.getFoodItems()?.let {
                    _foodCategories.value = it
                    it.categories.getOrNull(0)?.let {
                        getSelectedFoodHome(it)
                    }
                }
                Log.d("DEB_category", "Successfully retrieved category from API ")

            } catch (e: Exception) {
                Log.e("DEB_category", "Error at getting the category from API")
            }
        }
    }

    fun getSelectedFoodHome(category: FoodTypes) {
        viewModelScope.launch {
            try {
                _foodItems.value =
                    FoodApi.retrofitService.getCategoryFoodItems(category.strCategory)
                Log.d("DEB_meals", "Successfully retrieved meals from API")
            } catch (e: Exception) {
                Log.e("DEB_meals", "Error at getting the meals from API")
            }
        }
    }

    fun getSearchFood(newSearch: String?) {
        viewModelScope.launch {
            try {
                _foodSearch.value = FoodApi.retrofitService.getSearchFood(newSearch)
                _searchFoundResults.value = _foodSearch.value?.meals?.size
                Log.d(
                    "DEB_search", "Successfully retrieved search meals for API" +
                            "\n${foodSearch.value!!.meals}"
                )
            } catch (e: Exception) {
                Log.e("DEB_search", "Error at getting searched meals for API")
            }
        }
    }
}