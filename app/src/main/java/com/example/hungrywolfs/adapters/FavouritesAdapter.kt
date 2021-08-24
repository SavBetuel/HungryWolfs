package com.example.hungrywolfs.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.hungrywolfs.R
import com.example.hungrywolfs.network.FoodDetails
import com.example.hungrywolfs.network.FoodSelected
import com.orhanobut.hawk.Hawk

class FavouritesAdapter(private val clickListener: (idMeal: String?) -> Unit) :
    RecyclerView.Adapter<FavouritesAdapter.FavouritesViewHolder>() {

    private var userFavouritesFood = mutableListOf<FoodDetails?>()

    class FavouritesViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.favourites_item_image)
        val text: TextView = view.findViewById(R.id.favourites_card_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouritesViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.favourites_view_item, parent, false)
        return FavouritesViewHolder(layout)
    }

    override fun onBindViewHolder(holder: FavouritesViewHolder, position: Int) {
        holder.text.text = userFavouritesFood[position]?.strMeal

        userFavouritesFood[position]?.strMealThumb.let {
            holder.image.load(it?.toUri()) {
                placeholder(R.drawable.loading_animation)
                error(R.drawable.ic_broken_image)
            }
        }

        holder.itemView.setOnLongClickListener {
            userFavouritesFood.removeAt(position)

            Hawk.put("userFavouritesFood", userFavouritesFood)
            Log.d("hawk", "Hawk size: ${userFavouritesFood.size}")

            notifyDataSetChanged()
            true
        }

        holder.itemView.setOnClickListener{
            clickListener(userFavouritesFood[position]?.idMeal)
        }
    }

    override fun getItemCount() = userFavouritesFood.size

    fun setData(newData: MutableList<FoodDetails?>) {
        this.userFavouritesFood.clear()
        this.userFavouritesFood = newData
        notifyDataSetChanged()
    }
}