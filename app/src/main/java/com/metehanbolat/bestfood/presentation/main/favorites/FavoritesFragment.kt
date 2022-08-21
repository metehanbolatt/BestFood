package com.metehanbolat.bestfood.presentation.main.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.metehanbolat.bestfood.databinding.FragmentFavoritesBinding
import com.metehanbolat.bestfood.presentation.main.MainActivity
import com.metehanbolat.bestfood.presentation.main.MainActivityViewModel

class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainViewModel: MainActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        val view = binding.root

        mainViewModel = (activity as MainActivity).viewModel

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeFavorites()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeFavorites() {
        mainViewModel.favoritesMeals.observe(viewLifecycleOwner) { meals ->
            meals.forEach {
                println("Favorite yemekler: ${it.strMeal}")
            }
        }
    }
}