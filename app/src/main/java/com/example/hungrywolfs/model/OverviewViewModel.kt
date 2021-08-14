package com.example.hungrywolfs.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hungrywolfs.network.FoodApi
import com.example.hungrywolfs.network.FoodCategories
import com.example.hungrywolfs.network.FoodHomeFragment
import kotlinx.coroutines.launch
import java.lang.Exception

class OverviewViewModel : ViewModel() {

    private val _foodCategories = MutableLiveData<FoodCategories>()
    val foodCategories: LiveData<FoodCategories> = _foodCategories

    private val _foodHomeFragment = MutableLiveData<FoodHomeFragment>()
    val foodHomeFragment: LiveData<FoodHomeFragment> = _foodHomeFragment

    init {
        getCategoriesData()
    }

    private fun getCategoriesData() {
        viewModelScope.launch {
            try {
                _foodCategories.value = FoodApi.retrofitService.getCategories()
                Log.d("DEB_category", "Successfully retrieved category from API ")
            } catch (e: Exception) {
                Log.d("DEB_category", "Error at getting the category from API")
            }
        }
    }

    fun getSelectedFoodHome(categoriesIndex: Int) {
        viewModelScope.launch {
            try {
                _foodHomeFragment.value = FoodApi.retrofitService.getFoodHomeFragment(
                    foodCategories.value!!.categories[categoriesIndex].strCategory)
                Log.d("DEB_meals", "Successfully retrieved meals from API")
            } catch (e: Exception) {
                Log.d("DEB_meals", "Error at getting the meals from API")
            }
        }
    }
}