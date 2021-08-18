package com.example.hungrywolfs.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.hungrywolfs.R
import com.example.hungrywolfs.network.FoodTypes
import androidx.core.content.ContextCompat

class CategoriesAdapter(private val clickListener: (category: FoodTypes) -> Unit) :
    RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    private val data: MutableList<FoodTypes> = mutableListOf()

    var currentSelected = 0
    var lastSelected = 0

    class CategoriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.text_item)
        val redLine: View = view.findViewById(R.id.underline)
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

        if (currentSelected == position) {
            holder.textView.setTextColor(
                ContextCompat.getColor(
                    holder.textView.context,
                    R.color.red_background
                )
            )
            holder.redLine.visibility = View.VISIBLE
        } else {
            holder.textView.setTextColor(
                ContextCompat.getColor(
                    holder.textView.context,
                    R.color.custom_gray
                )
            )
            holder.redLine.visibility = View.INVISIBLE
        }

        holder.textView.setOnClickListener {
            clickListener(category)
            lastSelected = currentSelected
            currentSelected = position
            notifyItemChanged(position)
            notifyItemChanged(lastSelected)
        }
    }

    override fun getItemCount() = data.size

    fun setData(data: List<FoodTypes>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }
}

