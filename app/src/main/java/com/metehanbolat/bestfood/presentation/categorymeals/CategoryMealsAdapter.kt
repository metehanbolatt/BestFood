package com.metehanbolat.bestfood.presentation.categorymeals

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.metehanbolat.bestfood.databinding.MealItemBinding
import com.metehanbolat.bestfood.models.MealsByCategory

class CategoryMealsAdapter: RecyclerView.Adapter<CategoryMealsAdapter.CategoryMealsHolder>() {

    private var mealsList = ArrayList<MealsByCategory>()

    @SuppressLint("NotifyDataSetChanged")
    fun setMealsList(mealsList: List<MealsByCategory>) {
        this.mealsList = mealsList as ArrayList<MealsByCategory>
        notifyDataSetChanged()
    }

    inner class CategoryMealsHolder(val binding: MealItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryMealsHolder {
        return CategoryMealsHolder(MealItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CategoryMealsHolder, position: Int) {
        holder.binding.apply {
            Glide.with(root).load(mealsList[position].strMealThumb).into(imgMeal)
            tvMealName.text = mealsList[position].strMeal
        }
    }

    override fun getItemCount() = mealsList.size
}