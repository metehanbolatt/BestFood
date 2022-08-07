package com.metehanbolat.bestfood.presentation.mealdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.metehanbolat.bestfood.R
import com.metehanbolat.bestfood.databinding.ActivityMealBinding
import com.metehanbolat.bestfood.presentation.main.home.HomeFragment

class MealActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMealBinding

    private lateinit var mealId: String
    private lateinit var mealName: String
    private lateinit var mealThumb: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        getMealInformationFromIntent()
        setInformationInViews()
    }

    private fun setInformationInViews() {
        binding.apply {
            Glide.with(baseContext)
                .load(mealThumb)
                .into(imgMealDetail)

            collapsingToolbar.title = mealName
            collapsingToolbar.setCollapsedTitleTextColor(ContextCompat.getColor(baseContext, R.color.white))
            collapsingToolbar.setExpandedTitleColor(ContextCompat.getColor(baseContext, R.color.white))
        }

    }

    private fun getMealInformationFromIntent() {
        val intent = intent
        mealId = intent.getStringExtra(HomeFragment.MEAL_ID)!!
        mealName = intent.getStringExtra(HomeFragment.MEAL_NAME)!!
        mealThumb = intent.getStringExtra(HomeFragment.MEAL_THUMB)!!
    }
}