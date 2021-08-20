package com.example.hungrywolfs.model

import android.util.Log
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.load
import com.example.hungrywolfs.R
import com.example.hungrywolfs.SingleLiveEvent
import com.example.hungrywolfs.databinding.FragmentDetailsBinding
import com.example.hungrywolfs.network.FoodApi
import com.example.hungrywolfs.network.FoodClick
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {

    private val _foodDetails = MutableLiveData<FoodClick>()
    val foodDetails: LiveData<FoodClick> = _foodDetails

    private val _navigateBack = SingleLiveEvent<Any>()
    val navigateBack: LiveData<Any> = _navigateBack


    private val _listOfTags = MutableLiveData<List<String>>()
    val listOfTags: LiveData<List<String>> = _listOfTags

    fun callGoBack(){
        _navigateBack.call()
    }


    fun getDetails(idMeal: String) {
        viewModelScope.launch {
            try {
                _foodDetails.value = FoodApi.retrofitService.getDetails(idMeal)
                _listOfTags.value = foodDetails.value?.meals?.first()?.strTags?.split(",")
                Log.d("DEB_details", "Successfully retrieved meals details for API")
            } catch (e: Exception) {
                Log.e("DEB_details", "Error at getting meals details for API")
            }
        }
    }
}