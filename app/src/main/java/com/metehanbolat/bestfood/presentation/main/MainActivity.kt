package com.metehanbolat.bestfood.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.metehanbolat.bestfood.R
import com.metehanbolat.bestfood.database.MealDatabase
import com.metehanbolat.bestfood.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val viewModel: MainActivityViewModel by lazy {
        val mealDatabase = MealDatabase.getInstance(this)
        val homeFragmentViewModelFactory = MainActivityViewModelFactory(mealDatabase)
        ViewModelProvider(this, homeFragmentViewModelFactory)[MainActivityViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val bottomNavigation = binding.bottomNavigation
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController

        NavigationUI.setupWithNavController(bottomNavigation, navController)
    }
}