package com.metehanbolat.bestfood.presentation.main.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.metehanbolat.bestfood.database.MealDatabase
import com.metehanbolat.bestfood.models.*
import com.metehanbolat.bestfood.service.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragmentViewModel(
    private val mealDatabase: MealDatabase
): ViewModel() {

    private val _randomMeal = MutableLiveData<Meal>()
    val randomMeal: LiveData<Meal> = _randomMeal

    private val _popularItems = MutableLiveData<List<MealsByCategory>>()
    val popularItems: LiveData<List<MealsByCategory>> = _popularItems

    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>> = _categories

    private val _favoritesMeals = MutableLiveData<List<Meal>>(mealDatabase.mealDao().getAllMeals().value)
    val favoritesMeals: LiveData<List<Meal>> = _favoritesMeals

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
        RetrofitInstance.api.getPopularItem(categoryName).enqueue(object : Callback<MealsByCategoryList> {
            override fun onResponse(call: Call<MealsByCategoryList>, response: Response<MealsByCategoryList>) {
                response.body()?.let { mealsByCategoryList ->
                    mealsByCategoryList.meals?.let { popularItems ->
                        _popularItems.value = popularItems
                    }
                }
            }

            override fun onFailure(call: Call<MealsByCategoryList>, t: Throwable) {
                Log.d("HomeFragment", t.message.toString())
            }

        })
    }

    fun getCategories() {
        RetrofitInstance.api.getCategories().enqueue(object : Callback<CategoryList> {
            override fun onResponse(call: Call<CategoryList>, response: Response<CategoryList>) {
                response.body()?.let { categoryList ->
                    _categories.value = categoryList.categories
                }
            }

            override fun onFailure(call: Call<CategoryList>, t: Throwable) {
                Log.d("HomeFragment", t.message.toString())
            }

        })
    }
}