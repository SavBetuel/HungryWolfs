package com.example.hungrywolfs.network

data class FoodCategories(val categories: List<FoodTypes>)
data class FoodTypes(val strCategory: String)

data class FoodHomeFragment(val meals: List<FoodSelected>)
data class FoodSelected(val strMeal: String, val strMealThumb: String, val idMeal: String)

data class FoodSearch(val meals: List<FoodSearchDetails>)
data class FoodSearchDetails(val strMeal: String, val strMealThumb: String, val idMeal: String)

data class FoodClick(val meals: List<FoodClickDetails>)
data class FoodClickDetails(
    val idMeal: String,
    val strMealThumb: String?,
    val strMeal: String?,
    val strTags: String?,
    val strArea: String?,
    val strMeasure1: String?,
    val strMeasure2: String?,
    val strMeasure3: String?,
    val strIngredient1: String?,
    val strIngredient2: String?,
    val strIngredient3: String?,
    val strInstructions: String?
)