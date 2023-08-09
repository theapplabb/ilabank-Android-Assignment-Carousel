package com.example.androidexam.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.ListAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidexam.R
import com.example.androidexam.databinding.RecyclerItemBinding
import com.example.androidexam.models.RecyclerItem

class FoodAdapter : ListAdapter<RecyclerItem, RecyclerView.ViewHolder>(
object : DiffUtil.ItemCallback<RecyclerItem>() {
    override fun areItemsTheSame(old: RecyclerItem, new: RecyclerItem): Boolean = false
    override fun areContentsTheSame(old: RecyclerItem, new: RecyclerItem): Boolean = false
}
), Filterable {

    var photosList: ArrayList<RecyclerItem> = ArrayList()
    var photosListFiltered: ArrayList<RecyclerItem> = ArrayList()

    inner class ViewHolder(val binding: RecyclerItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun setData(item : RecyclerItem){
            binding.textView.text = item.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding : RecyclerItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recycler_item,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val defaultViewHolder: ViewHolder = holder as ViewHolder
        defaultViewHolder.setData(photosListFiltered[position])
        Glide.with(holder.itemView.context).load(photosListFiltered[position].image_url)
            .into(holder.binding.imageView)
    }

    override fun getItemCount(): Int {
        return photosListFiltered.size
    }

    fun addData(list: ArrayList<RecyclerItem>) {
        photosList = list
        photosListFiltered = photosList
        submitList(photosListFiltered)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint?.toString() ?: ""
                photosListFiltered = if (charString.isEmpty()) photosList else {
                    val filteredList = ArrayList<RecyclerItem>()
                    photosList
                        .filter {
                            (it.title.lowercase().contains(constraint ?: ""))
                        }
                        .forEach { filteredList.add(it) }
                    filteredList

                }
                return FilterResults().apply { values = photosListFiltered }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                photosListFiltered = if (results?.values == null)
                    ArrayList()
                else
                    results.values as ArrayList<RecyclerItem>
                    submitList(results?.values as ArrayList<RecyclerItem>)
            }
        }
    }
}