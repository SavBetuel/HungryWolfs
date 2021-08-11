package com.example.hungrywolfs

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.hungrywolfs.network.FoodApi
import com.example.hungrywolfs.network.FoodCategories

import com.example.hungrywolfs.network.FoodInformation
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Exception

class CategoriesAdapter(val viewModel: OverviewViewModel) :
    RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

//    private val categories = listOf<String>("Beef", "Chicken", "Dessert", "Lamb", "Miscellaneous",
//        "Pasta","Pork", "Seafood", "Side", "Starter", "Vegan","Vegetarian","Breakfast","Goat")

    init {
        runBlocking {
            val job = launch {
                try {
                    viewModel.setFoodInformation(FoodApi.retrofitService.getCategories())
                    Log.d("DEBUGGER", "Successfully retrieved data from API " +
                            "\n${viewModel.foodInformation.value?.categories}")
                } catch (e: Exception) {
                    Log.d("DEBUGGER", "Error at getting the data from API")
                }
            }
            job.join()
        }
    }


    class CategoriesViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val button = view.findViewById<TextView>(R.id.button_item)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.categories_view_item, parent, false)

        return CategoriesViewHolder(layout)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val categorie = viewModel.foodInformation.value?.categories?.get(position)?.strCategory
        holder.button.text = categorie.toString()
    }

    override fun getItemCount(): Int {
        return viewModel.foodInformation.value!!.categories.size
    }


}

