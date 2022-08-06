package com.metehanbolat.bestfood.presentation.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.metehanbolat.bestfood.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeViewModel: HomeFragmentViewModel

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

        homeViewModel.randomMeal.observe(viewLifecycleOwner) {
            it?.let {
                binding.randomMealProgress.visibility = View.INVISIBLE
                binding.refreshCardView.visibility = View.VISIBLE
                Glide.with(requireContext()).load(it.strMealThumb).into(binding.imgRandomMeal)
            }
        }

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