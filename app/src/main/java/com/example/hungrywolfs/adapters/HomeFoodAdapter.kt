package com.example.hungrywolfs.adapters

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.hungrywolfs.R
import com.example.hungrywolfs.network.FoodHomeFragment
import com.example.hungrywolfs.network.FoodSelected
import de.hdodenhof.circleimageview.CircleImageView

class HomeFoodAdapter: RecyclerView.Adapter<HomeFoodAdapter.FoodViewHolder>(){
    private val data: MutableList<FoodSelected> = mutableListOf()

    val testData = (1).rangeTo(28).toList()

    class FoodViewHolder(val view: View): RecyclerView.ViewHolder(view)
    {
        val text = view.findViewById<TextView>(R.id.text_food_home_screen)
        val image = view.findViewById<ImageView>(R.id.image_food_home_screen)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.food_view_item,parent,false)

        return FoodViewHolder(layout)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.text.text = data[position].strMeal

        data[position].strMealThumb?.let{
            var imgUri = it.toUri().buildUpon().scheme("https").build()
            holder.image.load(imgUri){
                placeholder(R.drawable.loading_animation)
                error(R.drawable.ic_broken_image)
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }


    fun setData(data: List<FoodSelected>){
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }
}