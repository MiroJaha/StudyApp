package com.example.studyapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.items_view.view.*

class RecyclerViewAdapter (private val titles:List<String>,private val materials:List<String>): RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>(){

    private lateinit var myListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener:onItemClickListener ){
        myListener=listener
    }

    class ItemViewHolder (itemView : View,listener: onItemClickListener): RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.items_view,
                parent,
                false
            )
        ,myListener
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val title=titles[position]
        val material=materials[position]


        holder.itemView.apply {
            Title.text=title
            Material.text=material
        }
    }

    override fun getItemCount(): Int {
        return titles.size
    }
}