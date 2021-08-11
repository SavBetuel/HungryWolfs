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
import kotlinx.coroutines.launch
import java.lang.Exception

class OverviewViewModel: ViewModel() {

    private val _foodInformation = MutableLiveData<FoodCategories>()
    val foodInformation: LiveData<FoodCategories> = _foodInformation

    init {
        getFoodInformation()
    }

    private fun getFoodInformation(){
        viewModelScope.launch {
            try {
                _foodInformation.value = FoodApi.retrofitService.getCategories()
                Log.d("DEBUGGER","Successfully retrieved data from API\nsize: ${foodInformation.value?.categories?.size}")
            } catch (e :Exception){
                Log.d("DEBUGGER","Error at getting the data from API")
            }

        }
    }



}