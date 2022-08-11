package com.metehanbolat.bestfood.presentation.main.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.metehanbolat.bestfood.models.CategoryList
import com.metehanbolat.bestfood.models.CategoryMeals
import com.metehanbolat.bestfood.models.Meal
import com.metehanbolat.bestfood.models.MealList
import com.metehanbolat.bestfood.service.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragmentViewModel: ViewModel() {

    private val _randomMeal = MutableLiveData<Meal>()
    val randomMeal: LiveData<Meal> = _randomMeal

    private val _popularItems = MutableLiveData<List<CategoryMeals>>()
    val popularItems: LiveData<List<CategoryMeals>> = _popularItems

    fun getRandomMeal() {
        RetrofitInstance.api.getRandomMeal().enqueue(object : Callback<MealList> {
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                response.body()?.let { mealList ->
                    val randomMeal = mealList.meals[0]
                    _randomMeal.value = randomMeal
                } ?: return
            }

            override fun onFailure(call: Call<MealList>, t: Throwable) {
                Log.d("HomeFragment", t.message.toString())
            }

        })
    }

    fun getPopularItems(categoryName: String) {
        RetrofitInstance.api.getPopularItem(categoryName).enqueue(object : Callback<CategoryList> {
            override fun onResponse(call: Call<CategoryList>, response: Response<CategoryList>) {
                response.body()?.let { categoryList ->
                    categoryList.meals?.let { popularItems ->
                        _popularItems.value = popularItems
                    }
                }
            }

            override fun onFailure(call: Call<CategoryList>, t: Throwable) {
                Log.d("HomeFragment", t.message.toString())
            }

        })
    }
}