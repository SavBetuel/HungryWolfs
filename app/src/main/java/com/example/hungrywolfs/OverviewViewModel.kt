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
import com.example.hungrywolfs.network.FoodInformation
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Exception

class OverviewViewModel: ViewModel() {

    private val _foodInformation = MutableLiveData<FoodCategories>()
    val foodInformation: LiveData<FoodCategories> = _foodInformation

    fun setFoodInformation(foodInformation: FoodCategories){
        _foodInformation.value = foodInformation
    }







}