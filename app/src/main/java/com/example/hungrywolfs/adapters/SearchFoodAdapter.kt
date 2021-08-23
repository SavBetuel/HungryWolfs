package com.example.hungrywolfs.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.hungrywolfs.R
import com.example.hungrywolfs.fragments.SearchFragmentDirections
import com.example.hungrywolfs.network.FoodSearchDetails

class SearchFoodAdapter(private val clickListener: (idMeal: String) -> Unit) : RecyclerView.Adapter<SearchFoodAdapter.SearchViewHolder>() {

    private val data = mutableListOf<FoodSearchDetails>()

    class SearchViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val text: TextView = view.findViewById(R.id.search_title)
        val image: ImageView = view.findViewById(R.id.search_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.food_search_item, parent, false)
        return SearchViewHolder(layout)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.text.text = data[position].strMeal

        val imgUri = data[position].strMealThumb.toUri()
        holder.image.load(imgUri) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }

        holder.itemView.setOnClickListener{
            clickListener(data[position].idMeal)
        }
    }

    override fun getItemCount() = data.size

    fun setData(data: List<FoodSearchDetails>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }
}