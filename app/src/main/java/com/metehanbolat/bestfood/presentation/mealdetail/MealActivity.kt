package com.metehanbolat.bestfood.presentation.mealdetail

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.metehanbolat.bestfood.R
import com.metehanbolat.bestfood.databinding.ActivityMealBinding
import com.metehanbolat.bestfood.presentation.main.home.HomeFragment

class MealActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMealBinding

    private lateinit var viewModel: MealActivityViewModel

    private lateinit var mealId: String
    private lateinit var mealName: String
    private lateinit var mealThumb: String
    private lateinit var youtubeLink: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this)[MealActivityViewModel::class.java]


        loadingCase()
        getMealInformationFromIntent()
        viewModel.getMealDetail(mealId)
        setInformationInViews()
        observeMealDetail()
        onYoutubeImageClick()
    }

    private fun onYoutubeImageClick() {
        binding.imgYoutube.setOnClickListener {
            Intent(Intent.ACTION_VIEW, Uri.parse(youtubeLink)).apply {
                startActivity(this)
            }
        }
    }

    private fun observeMealDetail() {
        viewModel.mealDetails.observe(this) {
            onResponseCase()
            val meal = it
            youtubeLink = meal.strYoutube.toString()
            binding.apply {
                tvCategory.text =
                    resources.getString(R.string.meal_detail_category, meal.strCategory)
                tvArea.text = resources.getString(R.string.meal_detail_area, meal.strArea)
                tvInstructionsDetail.text = meal.strInstructions
            }
        }
    }

    private fun setInformationInViews() {
        binding.apply {
            Glide.with(baseContext)
                .load(mealThumb)
                .into(imgMealDetail)

            collapsingToolbar.title = mealName
            collapsingToolbar.setCollapsedTitleTextColor(
                ContextCompat.getColor(
                    baseContext,
                    R.color.white
                )
            )
            collapsingToolbar.setExpandedTitleColor(
                ContextCompat.getColor(
                    baseContext,
                    R.color.white
                )
            )
        }

    }

    private fun getMealInformationFromIntent() {
        val intent = intent
        mealId = intent.getStringExtra(HomeFragment.MEAL_ID)!!
        mealName = intent.getStringExtra(HomeFragment.MEAL_NAME)!!
        mealThumb = intent.getStringExtra(HomeFragment.MEAL_THUMB)!!
    }

    private fun loadingCase() {
        binding.apply {
            linearProgressBar.visibility = View.VISIBLE
            buttonAddFav.visibility = View.INVISIBLE
            tvInstructions.visibility = View.INVISIBLE
            tvCategory.visibility = View.INVISIBLE
            tvArea.visibility = View.INVISIBLE
            imgYoutube.visibility = View.INVISIBLE
        }
    }

    private fun onResponseCase() {
        binding.apply {
            linearProgressBar.visibility = View.INVISIBLE
            buttonAddFav.visibility = View.VISIBLE
            tvInstructions.visibility = View.VISIBLE
            tvCategory.visibility = View.VISIBLE
            tvArea.visibility = View.VISIBLE
            imgYoutube.visibility = View.VISIBLE
        }
    }
}