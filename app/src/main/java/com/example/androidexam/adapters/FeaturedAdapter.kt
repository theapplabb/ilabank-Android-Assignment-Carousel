package com.example.androidexam.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidexam.R

class FeaturedAdapter(
    private val objectArrayList: ArrayList<Int>,
) : RecyclerView.Adapter<FeaturedAdapter.FeaturedViewHolder>() {

    class FeaturedViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val featuredImage: ImageView = view.findViewById(R.id.featured_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeaturedViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.item_featured_view, parent, false)
        return FeaturedViewHolder(view)
    }

    override fun onBindViewHolder(holder: FeaturedViewHolder, position: Int) {
        val featuredData = objectArrayList[position]
        holder.featuredImage.setImageResource(featuredData)
    }

    override fun getItemCount(): Int {
        return objectArrayList.size
    }
}






