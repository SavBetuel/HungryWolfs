package com.example.hungrywolfs.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.hungrywolfs.R
import com.example.hungrywolfs.fragments.HomeFragmentDirections
import com.example.hungrywolfs.network.FoodSelected

class HomeFoodAdapter : RecyclerView.Adapter<HomeFoodAdapter.FoodViewHolder>() {
    private val data = mutableListOf<FoodSelected>()

    class FoodViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val text: TextView = view.findViewById(R.id.text_food_home_screen)
        val image: ImageView = view.findViewById(R.id.image_food_home_screen)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.food_view_item, parent, false)

        return FoodViewHolder(layout)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.text.text = data[position].strMeal

        data[position].strMealThumb.let {
            var imgUri = it.toUri()
            holder.image.load(imgUri) {
                placeholder(R.drawable.loading_animation)
                error(R.drawable.ic_broken_image)
            }
        }

        holder.itemView.setOnClickListener{
            Log.d("DEB_details","You pressed the $position element, with id: ${data[position].idMeal}")
            val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(idMeal = data[position].idMeal)
            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(data: List<FoodSelected>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }
}