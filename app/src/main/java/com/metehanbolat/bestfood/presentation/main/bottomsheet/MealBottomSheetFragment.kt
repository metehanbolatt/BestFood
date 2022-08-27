package com.metehanbolat.bestfood.presentation.main.bottomsheet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.metehanbolat.bestfood.R
import com.metehanbolat.bestfood.databinding.FragmentMealBottomSheetBinding
import com.metehanbolat.bestfood.presentation.main.MainActivity
import com.metehanbolat.bestfood.presentation.main.MainActivityViewModel

private const val MEAL_ID = "param1"

class MealBottomSheetFragment : Fragment() {
    private var mealId: String? = null

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

    companion object {
        @JvmStatic
        fun newInstance(mealId: String) =
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
}