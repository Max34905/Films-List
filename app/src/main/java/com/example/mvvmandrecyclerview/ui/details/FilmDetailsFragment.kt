package com.example.mvvmandrecyclerview.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.mvvmandrecyclerview.databinding.FragmentFilmDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FilmDetailsFragment : Fragment() {
    private val args: FilmDetailsFragmentArgs by navArgs()
    private val viewModel: FilmsDetailsViewModel by viewModels()
    private lateinit var binding: FragmentFilmDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = setBinding()
        setActivityContent()
        return view
    }

    private fun setBinding(): View {
        binding = FragmentFilmDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    private fun setActivityContent() {
        val id = args.id
        viewModel.fetchFilmDetails(id)
        lifecycleScope.launch {
            viewModel.filmStateFlow.collect { film ->
                if (film != null) {
                    binding.filmImage.setImageResource(film.filmImage)
                    binding.filmImage.contentDescription = getString(film.imageDescription)
                    binding.filmDescription.setText(film.filmDescription)
                }
            }
        }
    }
}