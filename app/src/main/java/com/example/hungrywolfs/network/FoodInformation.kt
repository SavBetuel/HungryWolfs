package com.example.hungrywolfs.network

data class FoodCategories(val categories: List<FoodInformation>)

data class FoodInformation(val idCategory:String,
                           val strCategory:String,
                           val strCategoryThumb:String,
                           val strCategoryDescription:String)


