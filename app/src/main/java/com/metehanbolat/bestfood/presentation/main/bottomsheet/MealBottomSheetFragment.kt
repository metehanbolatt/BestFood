package com.metehanbolat.bestfood.presentation.main.bottomsheet

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.metehanbolat.bestfood.databinding.FragmentMealBottomSheetBinding
import com.metehanbolat.bestfood.presentation.main.MainActivity
import com.metehanbolat.bestfood.presentation.main.MainActivityViewModel
import com.metehanbolat.bestfood.presentation.main.home.HomeFragment
import com.metehanbolat.bestfood.presentation.mealdetail.MealActivity

private const val MEAL_ID = "param1"

class MealBottomSheetFragment : BottomSheetDialogFragment() {
    private var mealId: String? = null
    private var mealName: String? = null
    private var mealThumb: String? = null

    private var _binding: FragmentMealBottomSheetBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mealId = it.getString(MEAL_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMealBottomSheetBinding.inflate(inflater, container, false)
        val view = binding.root

        mainViewModel = (activity as MainActivity).viewModel

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mealId?.let { mainViewModel.getMealById(it) }

        observeBottomSheetMeal()
        onBottomSheetDialogClick()
    }

    companion object {
        @JvmStatic
        fun newInstance(mealId: String?) =
            MealBottomSheetFragment().apply {
                arguments = Bundle().apply {
                    putString(MEAL_ID, mealId)
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onBottomSheetDialogClick() {
        binding.bottomSheet.setOnClickListener {
            if (mealName != null && mealThumb != null) {
                Intent(requireActivity(), MealActivity::class.java).apply {
                    putExtra(HomeFragment.MEAL_ID, mealId)
                    putExtra(HomeFragment.MEAL_NAME, mealName)
                    putExtra(HomeFragment.MEAL_THUMB, mealThumb)
                    startActivity(this)
                }
            }
        }
    }

    private fun observeBottomSheetMeal() {
        mainViewModel.bottomSheetMeal.observe(viewLifecycleOwner) {
            binding.apply {
                Glide.with(this@MealBottomSheetFragment).load(it.strMealThumb).into(imgBottomSheet)
                tvBottomSheetArea.text = it.strArea
                tvBottomSheetCategory.text = it.strCategory
                tvBottomSheetMealName.text = it.strMeal

                mealName = it.strMeal
                mealThumb = it.strMealThumb
            }
        }
    }
}