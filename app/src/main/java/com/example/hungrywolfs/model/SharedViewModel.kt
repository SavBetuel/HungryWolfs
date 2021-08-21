package com.example.hungrywolfs.model

import android.util.Log
import android.view.View
import android.widget.CheckBox
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

    private var _userFavouritesFood: MutableList<Pair<String, String>> = mutableListOf()
    val userFavouritesFood: MutableList<Pair<String, String>> = _userFavouritesFood

    private val _userFavouritesIdMeals = mutableListOf<String>()
    val userFavouritesIdMeals: List<String> = _userFavouritesIdMeals


    fun storeData(view: View, foodName: String, imageUri: String, idMeal: String) {

        val checked = view as CheckBox
        Log.d("testtt", "foodName:${checked.isChecked} $foodName, imageUri: $imageUri")

        if (checked.isChecked) {
            _userFavouritesFood.add(Pair(foodName, imageUri))
            _userFavouritesIdMeals.add(idMeal)
        } else {
            if (_userFavouritesFood.contains(Pair(foodName, imageUri))) {
                _userFavouritesFood.remove(Pair(foodName, imageUri))
            }
            if (isSelected(idMeal)) _userFavouritesIdMeals.remove(idMeal)
        }

        Log.d(
            "testtt",
            "ListOfPairs: $userFavouritesFood\n:::::::::::::: size: ${userFavouritesFood.size}-------------------idMeals: ${_userFavouritesIdMeals}"
        )

    }

    fun isSelected(idMeal: String?): Boolean {
        return if (_userFavouritesIdMeals.isEmpty()) false
        else _userFavouritesIdMeals.contains(idMeal)
    }


}