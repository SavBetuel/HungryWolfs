package com.example.hungrywolfs.network

import com.example.hungrywolfs.ConstantVariables
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(ConstantVariables.BASE_CATEGORY_URL)
    .build()

interface FoodApiService {
    @GET("/api/json/v1/1/categories.php")
    suspend fun getFoodItems(): FoodCategories

    @GET("/api/json/v1/1/filter.php")
    suspend fun getCategoryFoodItems(@Query("c") filter: String?): FoodHomeFragment

    @GET("/api/json/v1/1/search.php")
    suspend fun getSearchFood(@Query("s") filter: String?): FoodSearch

    @GET("/api/json/v1/1/lookup.php")
    suspend fun getDetails(@Query("i") filter: String): Food
}

object FoodApi {
    val retrofitService: FoodApiService by lazy {
        retrofit.create(FoodApiService::class.java)
    }
}

