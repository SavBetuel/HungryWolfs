package com.example.hungrywolfs.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hungrywolfs.network.FoodDetails
import com.orhanobut.hawk.Hawk

class FavouritesViewModel:  ViewModel()  {

    private var _userFavouritesFood = MutableLiveData<MutableList<FoodDetails?>>()
    val userFavouritesFood: LiveData<MutableList<FoodDetails?>> = _userFavouritesFood

    fun getHawkData(){
        _userFavouritesFood.value = Hawk.get<MutableList<FoodDetails?>>("userFavouritesFood") ?: mutableListOf()
    }

    fun removeItem(position: Int){
        val temp = _userFavouritesFood.value
        temp?.removeAt(position)
        _userFavouritesFood.value = temp
        Hawk.put("userFavouritesFood", userFavouritesFood.value)
    }
}