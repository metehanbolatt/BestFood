package com.metehanbolat.bestfood.presentation.main.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.metehanbolat.bestfood.databinding.FragmentHomeBinding
import com.metehanbolat.bestfood.models.Meal
import com.metehanbolat.bestfood.presentation.categorymeals.CategoryMealsActivity
import com.metehanbolat.bestfood.presentation.main.MainActivity
import com.metehanbolat.bestfood.presentation.main.MainActivityViewModel
import com.metehanbolat.bestfood.presentation.mealdetail.MealActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainViewModel: MainActivityViewModel
    private lateinit var popularItemsAdapter: MostPopularAdapter
    private lateinit var categoriesAdapter: CategoriesAdapter

    private lateinit var randomMeal: Meal

    companion object {
        const val MEAL_ID = "com.metehanbolat.bestfood.presentation.main.home.idMeal"
        const val MEAL_NAME = "com.metehanbolat.bestfood.presentation.main.home.nameMeal"
        const val MEAL_THUMB = "com.metehanbolat.bestfood.presentation.main.home.thumbMeal"
        const val CATEGORY_NAME = "com.metehanbolat.bestfood.presentation.main.home.categoryName"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        mainViewModel = (activity as MainActivity).viewModel
        popularItemsAdapter = MostPopularAdapter()
        categoriesAdapter = CategoriesAdapter()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preparePopularItemsRecyclerView()
        prepareCategoriesRecyclerView()
        mainViewModel.getRandomMeal()
        observeRandomMeal()
        mainViewModel.getPopularItems("Seafood")
        observePopularItems()
        mainViewModel.getCategories()
        observeCategories()
        clicks()
    }

    private fun observeRandomMeal() {
        mainViewModel.randomMeal.observe(viewLifecycleOwner) {
            it?.let {
                binding.randomMealProgress.visibility = View.INVISIBLE
                binding.refreshCardView.visibility = View.VISIBLE
                Glide.with(requireContext()).load(it.strMealThumb).into(binding.imgRandomMeal)
                randomMeal = it
            }
        }
    }

    private fun observePopularItems() {
        mainViewModel.popularItems.observe(viewLifecycleOwner) {
            it?.let { popularItemsAdapter.setMeals(it as ArrayList) }
        }
    }

    private fun observeCategories() {
        mainViewModel.categories.observe(viewLifecycleOwner) {
            it?.let { categoriesAdapter.setCategoryList(it) }

        }
    }

    private fun preparePopularItemsRecyclerView() {
        binding.recViewMealsPopular.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = popularItemsAdapter
        }
    }

    private fun prepareCategoriesRecyclerView() {
        binding.recViewCategories.apply {
            layoutManager = GridLayoutManager(requireContext(), 3, GridLayoutManager.VERTICAL, false)
            adapter = categoriesAdapter
        }
    }

    private fun clicks() {
        binding.randomMealCard.setOnClickListener {
            Intent(requireContext(), MealActivity::class.java).apply {
                putExtra(MEAL_ID, randomMeal.idMeal)
                putExtra(MEAL_NAME, randomMeal.strMeal)
                putExtra(MEAL_THUMB, randomMeal.strMealThumb)
                startActivity(this)
            }
        }

        binding.refreshCardView.setOnClickListener {
            binding.refreshCardView.visibility = View.INVISIBLE
            binding.randomMealProgress.visibility = View.VISIBLE
            mainViewModel.getRandomMeal()
        }

        popularItemsAdapter.onItemClick = { meal ->
            Intent(requireContext(), MealActivity::class.java).apply {
                putExtra(MEAL_ID, meal.idMeal)
                putExtra(MEAL_NAME, meal.strMeal)
                putExtra(MEAL_THUMB, meal.strMealThumb)
                startActivity(this)
            }
        }

        categoriesAdapter.onItemClick = { category ->
            Intent(requireContext(), CategoryMealsActivity::class.java).apply {
                putExtra(CATEGORY_NAME, category.strCategory)
                startActivity(this)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}