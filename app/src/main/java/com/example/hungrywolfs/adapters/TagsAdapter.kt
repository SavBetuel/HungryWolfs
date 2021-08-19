package com.example.hungrywolfs.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.hungrywolfs.R
import com.example.hungrywolfs.network.FoodSelected

class TagsAdapter : RecyclerView.Adapter<TagsAdapter.TagsViewHolder>() {

    private var data = mutableListOf<String>("No tags")



    class TagsViewHolder(view : View): RecyclerView.ViewHolder(view){
        val button: Button = view.findViewById(R.id.button_tag)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagsViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.tag_button, parent, false)
        return TagsViewHolder(layout)
    }

    override fun onBindViewHolder(holder: TagsViewHolder, position: Int) {
        var tags = data.first().split(",")
        holder.button.text = tags[position]

    }

    override fun getItemCount() = data.first().split(",").size

    fun setData(data: String?){
        data?.let {
            this.data.set(0,it)
        }
        notifyDataSetChanged()

        Log.d("My_data", "data = ${this.data.first()}")
    }
}