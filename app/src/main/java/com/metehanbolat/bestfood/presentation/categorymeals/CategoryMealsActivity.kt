package com.metehanbolat.bestfood.presentation.categorymeals

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.metehanbolat.bestfood.databinding.ActivityCategoryMealsBinding

class CategoryMealsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryMealsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryMealsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


    }
}