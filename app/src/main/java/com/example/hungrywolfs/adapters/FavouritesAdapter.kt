package com.example.hungrywolfs.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.hungrywolfs.R

class FavouritesAdapter(private val userFavouritesFood: MutableList<Pair<String, String>>) :
    RecyclerView.Adapter<FavouritesAdapter.FavouritesViewHolder>() {

    var userFavourites: MutableList<Pair<String, String>> = userFavouritesFood


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
        holder.text.text = userFavourites[position].first
        userFavourites[position].second.let {
            holder.image.load(it.toUri()) {
                placeholder(R.drawable.loading_animation)
                error(R.drawable.ic_broken_image)
            }
        }

        holder.itemView.setOnClickListener {
            userFavourites.removeAt(position)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount() = userFavourites.size
}