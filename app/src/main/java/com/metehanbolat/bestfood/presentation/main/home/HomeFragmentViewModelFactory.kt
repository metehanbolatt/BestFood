package com.metehanbolat.bestfood.presentation.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.metehanbolat.bestfood.database.MealDatabase

class HomeFragmentViewModelFactory(
    private val mealDatabase: MealDatabase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MealDatabase::class.java).newInstance(mealDatabase)
    }
}