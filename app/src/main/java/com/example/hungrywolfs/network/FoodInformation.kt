package com.example.hungrywolfs.network

data class FoodCategories(val categories: List<FoodTypes>)
data class FoodTypes(val strCategory: String)

data class FoodHomeFragment(val meals: List<FoodSelected>)
data class FoodSelected(val strMeal: String, val strMealThumb: String)

data class FoodSearch(val meals: List<FoodSearchDetails>)
data class FoodSearchDetails(val strMeal: String, val strMealThumb: String)


