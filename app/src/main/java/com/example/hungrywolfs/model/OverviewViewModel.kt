package com.example.hungrywolfs.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hungrywolfs.network.FoodApi
import com.example.hungrywolfs.network.FoodCategories
import com.example.hungrywolfs.network.FoodHomeFragment
import com.example.hungrywolfs.network.FoodTypes
import kotlinx.coroutines.launch
import java.lang.Exception

class OverviewViewModel : ViewModel() {

    private val _foodCategories = MutableLiveData<FoodCategories>()
    val foodCategories: LiveData<FoodCategories> = _foodCategories

    private val _foodItems = MutableLiveData<FoodHomeFragment>()
    val foodItems: LiveData<FoodHomeFragment> = _foodItems

    init {
        getCategoriesData()
    }

    private fun getCategoriesData() {
        viewModelScope.launch {
            try {
                FoodApi.retrofitService.getFoodItems()?.let{
                    _foodCategories.value = it
                    it.categories.getOrNull(0)?.let{
                        getSelectedFoodHome(it)
                    }
                }
                Log.d("DEB_category", "Successfully retrieved category from API ")

            } catch (e: Exception) {
                Log.d("DEB_category", "Error at getting the category from API")
            }
        }
    }

    fun getSelectedFoodHome(category: FoodTypes) {
        viewModelScope.launch {
            try {
                _foodItems.value = FoodApi.retrofitService.getCategoryFoodItems(category.strCategory)
                Log.d("DEB_meals", "Successfully retrieved meals from API")
            } catch (e: Exception) {
                Log.d("DEB_meals", "Error at getting the meals from API")
            }
        }
    }
}