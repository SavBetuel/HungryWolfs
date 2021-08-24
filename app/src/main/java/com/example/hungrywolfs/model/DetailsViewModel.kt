package com.example.hungrywolfs.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hungrywolfs.SingleLiveEvent
import com.example.hungrywolfs.network.FoodApi
import com.example.hungrywolfs.network.FoodDetails
import com.orhanobut.hawk.Hawk
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

    private var _userFavouritesFood: MutableList<FoodDetails?> = mutableListOf()

    init{
        _userFavouritesFood = Hawk.get<MutableList<FoodDetails?>>("userFavouritesFood") ?: mutableListOf()
    }

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

    fun addItemFavourites(state: Boolean, item: FoodDetails?) {
        if (state) {
            _userFavouritesFood.add(item)
        } else {
            _userFavouritesFood.remove(item)
        }
        Hawk.put("userFavouritesFood", _userFavouritesFood)
    }

    fun isSelected(item: FoodDetails?): Boolean {
        return if (_userFavouritesFood.isEmpty()) false
        else _userFavouritesFood.contains(item)
    }
}