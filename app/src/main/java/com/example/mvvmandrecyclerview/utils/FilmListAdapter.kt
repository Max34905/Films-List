package com.example.mvvmandrecyclerview.utils

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmandrecyclerview.model.Film
import com.example.mvvmandrecyclerview.model.Genre
import com.example.mvvmandrecyclerview.ui.film_list_items.FilmItemViewHolder
import com.example.mvvmandrecyclerview.ui.film_list_items.RecyclerViewInterface

class FilmListAdapter (
    private val recyclerViewInterface: RecyclerViewInterface
) : RecyclerView.Adapter<FilmItemViewHolder>() {
    private var filmList: List<Film> = listOf()
    private var originalFilmList: List<Film> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmItemViewHolder {
        return FilmItemViewHolder.from(parent, recyclerViewInterface)
    }

    override fun onBindViewHolder(holder: FilmItemViewHolder, position: Int) {
        holder.bind(filmList[position])
    }

    override fun getItemCount(): Int {
        return filmList.size
    }

    fun setFilmList(newFilmList: List<Film>) {
        val diffResult = DiffUtil.calculateDiff(FilmListDiffCallback(filmList, newFilmList))
        filmList = newFilmList
        originalFilmList = newFilmList
        diffResult.dispatchUpdatesTo(this)
    }

    fun filter(isFilterSwitchChecked: Boolean) {
        val newFilmList = if (isFilterSwitchChecked) {
            originalFilmList.filter { it.genre.equals(Genre.SUPER_HERO) }
        } else {
            originalFilmList
        }

        val diffResult = DiffUtil.calculateDiff(FilmListDiffCallback(filmList, newFilmList))
        filmList = newFilmList
        diffResult.dispatchUpdatesTo(this)
    }
}