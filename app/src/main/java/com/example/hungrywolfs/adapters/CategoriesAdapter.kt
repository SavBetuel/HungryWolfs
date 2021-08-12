package com.example.hungrywolfs.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.hungrywolfs.R
import com.example.hungrywolfs.network.FoodTypes

class CategoriesAdapter :
    RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {


    private val data: MutableList<FoodTypes> = mutableListOf()

    private val _selectedCategory = MutableLiveData<Int>()
    val selectedCategory: LiveData<Int> = _selectedCategory



    class CategoriesViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val textView = view.findViewById<TextView>(R.id.text_item)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.categories_view_item, parent, false)

        return CategoriesViewHolder(layout)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val category = data[position]
        holder.textView.text = category.strCategory

        holder.textView.setOnClickListener{
            _selectedCategory.value=position
        }
    }

    override fun getItemCount() = data.size

    fun setData(data: List<FoodTypes>){
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
        _selectedCategory.value=0
    }




}

