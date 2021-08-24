package com.example.hungrywolfs.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hungrywolfs.network.FoodDetails
import com.orhanobut.hawk.Hawk

class FavouritesViewModel:  ViewModel()  {

    private var _userFavouritesFood: MutableLiveData<MutableList<FoodDetails?>> = MutableLiveData<MutableList<FoodDetails?>>()
    val userFavouritesFood: LiveData<MutableList<FoodDetails?>> = _userFavouritesFood

    init {
        _userFavouritesFood.value = Hawk.get<MutableList<FoodDetails?>>("userFavouritesFood") ?: mutableListOf()
    }

    fun removeItem(position: Int){
        _userFavouritesFood.value?.removeAt(position)
        Hawk.put("userFavouritesFood", userFavouritesFood.value)
    }
}