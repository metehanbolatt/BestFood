package com.metehanbolat.bestfood.presentation.main.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.metehanbolat.bestfood.databinding.CategoryItemBinding
import com.metehanbolat.bestfood.models.Category

class CategoriesAdapter : RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {

    private var categoriesList = ArrayList<Category>()
    var onItemClick: ((Category) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setCategoryList(categoriesList: List<Category>) {
        this.categoriesList = categoriesList as ArrayList<Category>
        notifyDataSetChanged()
    }

    inner class CategoryViewHolder(val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            CategoryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.binding.apply {
            Glide.with(holder.itemView).load(categoriesList[position].strCategoryThumb).into(imgCategory)
            tvCategoryName.text = categoriesList[position].strCategory
            root.setOnClickListener { onItemClick?.invoke(categoriesList[position]) }
        }
    }

    override fun getItemCount() = categoriesList.size


}