package com.example.hungrywolfs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

import com.example.hungrywolfs.network.FoodInformation

class CategoriesAdapter : RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    private val categories = listOf<String>("Beef", "Chicken", "Dessert", "Lamb", "Miscellaneous",
        "Pasta","Pork", "Seafood", "Side", "Starter", "Vegan","Vegetarian","Breakfast","Goat")

    class CategoriesViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val button = view.findViewById<TextView>(R.id.button_item)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.categories_view_item,parent,false)

        return CategoriesViewHolder(layout)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val categorie = categories.get(position)
        holder.button.text = categorie.toString()
    }

    override fun getItemCount(): Int {
        return categories.size
    }


}

