package com.example.hungrywolfs.model

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.widget.CheckBox

import androidx.lifecycle.ViewModel

class ActivityViewModel: ViewModel() {

private var _userFavouritesFood: MutableList<Pair<String, String>> = mutableListOf()
    val userFavouritesFood: List<Pair<String, String>> = _userFavouritesFood


   fun storeData(view: View, foodName: String, imageUri: String){

        val checked = view as CheckBox
       Log.d("testtt","foodName:${checked.isChecked} $foodName, imageUri: $imageUri")

       if(checked.isChecked){
            _userFavouritesFood.add(Pair(foodName, imageUri))
       } else{
           if(_userFavouritesFood.contains(Pair(foodName, imageUri))) {
               _userFavouritesFood.remove(Pair(foodName, imageUri))
           }
       }

       Log.d("testtt","ListOfPairs: $userFavouritesFood\n:::::::::::::: size: ${userFavouritesFood.size}")

   }
}