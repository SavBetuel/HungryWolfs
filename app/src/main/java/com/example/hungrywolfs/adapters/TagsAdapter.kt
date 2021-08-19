package com.example.hungrywolfs.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.hungrywolfs.R

class TagsAdapter : RecyclerView.Adapter<TagsAdapter.TagsViewHolder>() {

    private var listOfTags = mutableListOf<String>()

    class TagsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val button: Button = view.findViewById(R.id.button_tag)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagsViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.tag_button, parent, false)
        return TagsViewHolder(layout)
    }

    override fun onBindViewHolder(holder: TagsViewHolder, position: Int) {
        holder.button.text = listOfTags[position]
    }

    override fun getItemCount() = listOfTags.size

    fun setTags(newListOfTags: List<String>) {
        this.listOfTags.clear()
        this.listOfTags.addAll(newListOfTags)
        notifyDataSetChanged()
    }
}