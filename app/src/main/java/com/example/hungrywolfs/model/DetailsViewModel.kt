package com.example.hungrywolfs.model

import android.service.autofill.Validators.not
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hungrywolfs.SingleLiveEvent
import com.example.hungrywolfs.network.FoodApi
import com.example.hungrywolfs.network.FoodDetails
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {

    private val _foodDetails = MutableLiveData<FoodDetails>()
    val foodDetails: LiveData<FoodDetails> = _foodDetails

    private val _navigateBack = SingleLiveEvent<Any>()
    val navigateBack: LiveData<Any> = _navigateBack

    private val _listOfTags = MutableLiveData<List<String>>()
    val listOfTags: LiveData<List<String>> = _listOfTags

    private var _buttonState = MutableLiveData<Boolean>()
    val buttonStatus: LiveData<Boolean> = _buttonState

    fun callGoBack(){
        _navigateBack.call()
    }

    fun getDetails(idMeal: String) {
        viewModelScope.launch {
            try {
                _foodDetails.value = FoodApi.retrofitService.getDetails(idMeal).meals.firstOrNull()
                _listOfTags.value = foodDetails.value?.strTags?.split(",") ?: emptyList()
            } catch (e: Exception) {
                Log.e("DEB_details", "Error at getting meals details for API")
            }
        }
    }

    fun onClickButton(){
        _buttonState.value = _buttonState.value != true
    }
}