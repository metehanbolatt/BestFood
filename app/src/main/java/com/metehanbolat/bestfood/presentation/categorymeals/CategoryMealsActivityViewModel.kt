package com.metehanbolat.bestfood.presentation.categorymeals

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.metehanbolat.bestfood.models.MealsByCategory
import com.metehanbolat.bestfood.models.MealsByCategoryList
import com.metehanbolat.bestfood.service.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryMealsActivityViewModel: ViewModel() {

    private val _meals = MutableLiveData<List<MealsByCategory>>()
    val meals: LiveData<List<MealsByCategory>> = _meals

    fun getMealsByCategory(categoryName: String) {
        RetrofitInstance.api.getMealsByCategory(categoryName).enqueue(object :  Callback<MealsByCategoryList> {
            override fun onResponse(
                call: Call<MealsByCategoryList>,
                response: Response<MealsByCategoryList>
            ) {
                response.body()?.let { mealsList ->
                    _meals.value = mealsList.meals!!
                }
            }

            override fun onFailure(call: Call<MealsByCategoryList>, t: Throwable) {
                Log.e("CategoryMealsActivityViewModel", t.message.toString())
            }

        })
    }
}