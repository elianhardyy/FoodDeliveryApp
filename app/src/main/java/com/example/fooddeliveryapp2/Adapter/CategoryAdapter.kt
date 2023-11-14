package com.example.fooddeliveryapp2.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fooddeliveryapp2.Data.Category
import com.example.fooddeliveryapp2.R

class CategoryAdapter(private val category:ArrayList<Category>) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    private lateinit var mListener : onItemClickListener
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var categoryTitle : TextView = itemView.findViewById(R.id.titleViewcategory)
        var categoryPicture : ImageView = itemView.findViewById(R.id.pictureViewcategory)
        var mainLayout : ConstraintLayout = itemView.findViewById(R.id.mainLayout)
    }
    interface onItemClickListener{
        fun onItemClick(position:Int)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_category,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.ViewHolder, position: Int) {
        val currentCat = category[position]
        holder.categoryTitle.text = currentCat.title
        var picUrl:String = ""
        when(position){
            0 -> picUrl = "cat_1"
            1 -> picUrl = "cat_2"
            2 -> picUrl = "cat_3"
            3 -> picUrl = "cat_4"
            4 -> picUrl = "cat_5"
            else -> {}
        }
        var drawableResourceId : Int = holder.itemView.context.resources.getIdentifier(picUrl,"drawable",holder.itemView.context.packageName)
        Glide.with(holder.itemView.context).load(drawableResourceId).into(holder.categoryPicture)
    }

    override fun getItemCount(): Int {
        return category.size
    }
}