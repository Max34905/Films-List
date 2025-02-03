package com.example.mvvmandrecyclerview.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.navOptions
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmandrecyclerview.R
import com.example.mvvmandrecyclerview.databinding.FragmentFilmListBinding
import com.example.mvvmandrecyclerview.ui.film_list_items.FilmItemMarginDecoration
import com.example.mvvmandrecyclerview.ui.film_list_items.RecyclerViewInterface
import com.example.mvvmandrecyclerview.utils.FilmListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FilmListFragment : Fragment(), RecyclerViewInterface {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: FragmentFilmListBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = setBinding()
        navController = Navigation.findNavController(requireActivity(), R.id.navHost)
        val filmsAdapter = setRecyclerView()
        setSwitchListener(filmsAdapter)
        return view
    }

    private fun setBinding(): View {
        binding = FragmentFilmListBinding.inflate(layoutInflater)
        return binding.root
    }

    private fun setRecyclerView(): FilmListAdapter {
        val filmsAdapter = FilmListAdapter(this)

        val recyclerView: RecyclerView = binding.filmsList
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = filmsAdapter

        lifecycleScope.launch {
            viewModel.filmListStateFlow.collect { films ->
                filmsAdapter.setFilmList(films)
            }
        }

        val margin = resources.getDimensionPixelSize(R.dimen.item_margin)
        recyclerView.addItemDecoration(FilmItemMarginDecoration(margin))
        return filmsAdapter
    }

    private fun setSwitchListener(filmsAdapter: FilmListAdapter) {
        binding.filterSwitch.setOnCheckedChangeListener { view, isChecked ->
            filmsAdapter.filter(isChecked)
        }
    }

    override fun onItemClick(id: Int) {
        val action = FilmListFragmentDirections.actionFilmListFragmentToFilmDetailsFragment(id)
        navController.navigate(action)
    }
}