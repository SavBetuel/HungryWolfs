package com.example.hungrywolfs

import android.util.Log
import androidx.annotation.RequiresOptIn
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hungrywolfs.network.FoodApi
import com.example.hungrywolfs.network.FoodApiService
import com.example.hungrywolfs.network.FoodCategories
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Exception

class OverviewViewModel: ViewModel() {

    private val _foodCategories = MutableLiveData<FoodCategories>()
    val foodCategories: LiveData<FoodCategories> = _foodCategories



    init {
        getCategoriesData()
    }

    private fun getCategoriesData(){
        viewModelScope.launch {
            try {
                _foodCategories.value = FoodApi.retrofitService.getCategories()
                Log.d("DEBUGGER", "Successfully retrieved data from API " +
                        "\n${_foodCategories.value?.categories}")
            } catch (e: Exception) {
                Log.d("DEBUGGER", "Error at getting the data from API")
            }
        }
    }










}