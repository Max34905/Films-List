package com.example.mvvmandrecyclerview.ui.film_list_items

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmandrecyclerview.databinding.FilmCardItemBinding
import com.example.mvvmandrecyclerview.model.Film

class FilmItemViewHolder (
    private val binding: FilmCardItemBinding,
    private val recyclerViewInterface: RecyclerViewInterface
) : RecyclerView.ViewHolder(binding.root) {

        fun bind(film: Film) {
            binding.filmImage.setImageResource(film.filmImage)
            binding.filmTitle.setText(film.filmTitle)

            binding.filmLayoutItem.setOnClickListener { view ->
                recyclerViewInterface.onItemClick(film.id)
            }
        }

        companion object {
            fun from(parent: ViewGroup, recyclerViewInterface: RecyclerViewInterface): FilmItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FilmCardItemBinding.inflate(layoutInflater)
                return FilmItemViewHolder(binding, recyclerViewInterface)
            }
        }
}