package com.example.hungrywolfs.model

import android.util.Log
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.load
import com.example.hungrywolfs.R
import com.example.hungrywolfs.databinding.FragmentDetailsBinding
import com.example.hungrywolfs.network.FoodApi
import com.example.hungrywolfs.network.FoodClick
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel(){

    private val _foodDetails = MutableLiveData<FoodClick>()
    val foodDetails: LiveData<FoodClick> = _foodDetails


    fun getDetails(idMeal: String) {
        viewModelScope.launch {
            try {
                _foodDetails.value = FoodApi.retrofitService.getDetails(idMeal)
                Log.d(
                    "DEB_details", "Successfully retrieved meals details for API\n" +
                            "${_foodDetails.value!!.meals}\ntest: ${_foodDetails.value?.meals?.first()?.strMeal.toString()}"
                )
            } catch (e: Exception) {
                Log.e("DEB_details", "Error at getting meals details for API")
            }
        }
    }

    fun setDetailsScreen(binding: FragmentDetailsBinding) {
        viewModelScope.launch {
            delay(2000)

            var imgUri = _foodDetails.value?.meals?.get(0)?.strMealThumb?.toUri()
            binding.foodImage.load(imgUri) {
                placeholder(R.drawable.loading_animation)
                error(R.drawable.ic_broken_image)
            }

            binding.foodTitle.text = _foodDetails.value?.meals?.first()?.strMeal
            Log.d(
                "DEB_details", "here I am in setDetailsScreen fun\n" +
                        "test2: ${_foodDetails.value?.meals?.first()?.strMeal}"
            )

            binding.buttonOne.text = _foodDetails.value?.meals?.first()?.strTags
            binding.buttonTwo.text = _foodDetails.value?.meals?.first()?.strArea
            binding.ingredientOne.text =
                _foodDetails.value?.meals?.first()?.strIngredient1 + " " + _foodDetails.value?.meals?.first()?.strMeasure1
            binding.ingredientTwo.text =
                _foodDetails.value?.meals?.first()?.strIngredient2 + " " + _foodDetails.value?.meals?.first()?.strMeasure2
            binding.ingredientTree.text =
                _foodDetails.value?.meals?.first()?.strIngredient3 + " " + _foodDetails.value?.meals?.first()?.strMeasure3

            binding.instruction.text = _foodDetails.value?.meals?.first()?.strInstructions

        }
    }
}