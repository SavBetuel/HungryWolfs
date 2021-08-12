package com.example.hungrywolfs.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.hungrywolfs.R
import com.example.hungrywolfs.network.FoodTypes

class CategoriesAdapter :
    RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {


    private val data: MutableList<FoodTypes> = mutableListOf()

    class CategoriesViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val button = view.findViewById<Button>(R.id.button_item)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.categories_view_item, parent, false)

        return CategoriesViewHolder(layout)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val category = data[position]
        holder.button.text = category.strCategory

        holder.button.setOnClickListener{

        }



    }

    override fun getItemCount() = data.size

    fun setData(data: List<FoodTypes>){
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

}

