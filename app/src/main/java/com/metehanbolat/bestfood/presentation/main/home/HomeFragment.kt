package com.metehanbolat.bestfood.presentation.main.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.metehanbolat.bestfood.databinding.FragmentHomeBinding
import com.metehanbolat.bestfood.models.Meal
import com.metehanbolat.bestfood.presentation.mealdetail.MealActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeViewModel: HomeFragmentViewModel

    private lateinit var randomMeal: Meal

    companion object {
        const val MEAL_ID = "com.metehanbolat.bestfood.presentation.main.home.idMeal"
        const val MEAL_NAME = "com.metehanbolat.bestfood.presentation.main.home.nameMeal"
        const val MEAL_THUMB = "com.metehanbolat.bestfood.presentation.main.home.thumbMeal"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        homeViewModel = ViewModelProvider(this)[HomeFragmentViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeRandomMeal()
        clicks()

    }

    private fun observeRandomMeal() {
        homeViewModel.randomMeal.observe(viewLifecycleOwner) {
            it?.let {
                binding.randomMealProgress.visibility = View.INVISIBLE
                binding.refreshCardView.visibility = View.VISIBLE
                Glide.with(requireContext()).load(it.strMealThumb).into(binding.imgRandomMeal)
                randomMeal = it
            }
        }
    }

    private fun clicks() {
        /** Random Meal Card to Meal Detail */
        binding.randomMealCard.setOnClickListener {
            Intent(requireContext(), MealActivity::class.java).apply {
                putExtra(MEAL_ID, randomMeal.idMeal)
                putExtra(MEAL_NAME, randomMeal.strMeal)
                putExtra(MEAL_THUMB, randomMeal.strMealThumb)
                startActivity(this)
            }
        }

        /** Refresh Random Meal */
        binding.refreshCardView.setOnClickListener {
            binding.refreshCardView.visibility = View.INVISIBLE
            binding.randomMealProgress.visibility = View.VISIBLE
            homeViewModel.getRandomMeal()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}