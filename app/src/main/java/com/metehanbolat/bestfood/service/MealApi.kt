package com.metehanbolat.bestfood.service

import com.metehanbolat.bestfood.models.MealList
import retrofit2.Call
import retrofit2.http.GET

interface MealApi {

    @GET("random.php")
    fun getRandomMeal(): Call<MealList>
}