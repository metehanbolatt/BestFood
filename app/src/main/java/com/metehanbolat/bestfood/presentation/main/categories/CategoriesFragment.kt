package com.metehanbolat.bestfood.presentation.main.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.metehanbolat.bestfood.databinding.FragmentCategoriesBinding
import com.metehanbolat.bestfood.presentation.main.MainActivity
import com.metehanbolat.bestfood.presentation.main.MainActivityViewModel
import com.metehanbolat.bestfood.presentation.main.home.CategoriesAdapter

class CategoriesFragment : Fragment() {

    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!

    private lateinit var categoriesAdapter: CategoriesAdapter
    private lateinit var mainViewModel: MainActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)

        mainViewModel = (activity as MainActivity).viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareRecyclerView()
        observeCategories()
    }

    private fun observeCategories() {
        mainViewModel.categories.observe(viewLifecycleOwner) { categories ->
            categoriesAdapter.setCategoryList(categories)
        }
    }

    private fun prepareRecyclerView() {
        categoriesAdapter = CategoriesAdapter()
        binding.rvCategories.apply {
            layoutManager = GridLayoutManager(requireContext(), 3, GridLayoutManager.VERTICAL, false)
            adapter = categoriesAdapter
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}