package com.example.hungrywolfs.model

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.hungrywolfs.network.Food
import com.example.hungrywolfs.network.FoodDetails
import com.orhanobut.hawk.Hawk

class SharedViewModel : ViewModel() {

    var userFavouritesFood: MutableList<FoodDetails?> = mutableListOf()

    init {
        getDataFromHawk()
        Log.d("SizeVMandAdapter", "init{} ")
    }

    fun addItemFavourites(state: Boolean, item: FoodDetails?) {
        if (state) {
            userFavouritesFood.add(item)
        } else {
            userFavouritesFood.remove(item)
        }
        Log.d("SizeVMandAdapter", "size of userFavouritesFood: ${userFavouritesFood.size}")
        updateHawk()
    }

    fun isSelected(item: FoodDetails?): Boolean {
        return if (userFavouritesFood.isEmpty()) false
        else userFavouritesFood.contains(item)
    }

    fun updateHawk(){
        Hawk.deleteAll()
        Hawk.put("userFavouritesFood", userFavouritesFood)
        Log.d("hawk", "Hawk size: ${userFavouritesFood.size}")
    }

    fun getDataFromHawk(){
        Hawk.get<MutableList<FoodDetails?>>("userFavouritesFood")?.let {
            userFavouritesFood= it
        }
        Log.d("hawk", "\n\nRetrieved data, size: ${userFavouritesFood.size}")
    }
}