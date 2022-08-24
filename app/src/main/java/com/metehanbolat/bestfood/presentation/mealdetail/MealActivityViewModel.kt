package com.metehanbolat.bestfood.presentation.mealdetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.metehanbolat.bestfood.database.MealDatabase
import com.metehanbolat.bestfood.models.Meal
import com.metehanbolat.bestfood.models.MealList
import com.metehanbolat.bestfood.service.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealActivityViewModel(
    private val mealDatabase: MealDatabase
): ViewModel() {

    private val _mealDetails = MutableLiveData<Meal>()
    val mealDetails: LiveData<Meal> = _mealDetails

    fun getMealDetail(id: String) {
        RetrofitInstance.api.getMealDetails(id).enqueue(object : Callback<MealList> {
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                if (response.body() != null) {
                    _mealDetails.value = response.body()!!.meals[0]
                } else return
            }

            override fun onFailure(call: Call<MealList>, t: Throwable) {
                Log.d("MealActivity", "Get Meal Detail Failure: ${t.message.toString()}")
            }

        })
    }

    fun insertMeal(meal: Meal) { viewModelScope.launch { mealDatabase.mealDao().upsert(meal) } }

}