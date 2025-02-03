package com.example.mvvmandrecyclerview.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.mvvmandrecyclerview.model.Film

class FilmListDiffCallback(
    private val oldFilmList: List<Film>,
    private val newFilmList: List<Film>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldFilmList.size
    override fun getNewListSize(): Int = newFilmList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldFilmList[oldItemPosition].id == newFilmList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldFilmList[oldItemPosition] == newFilmList[newItemPosition]
    }

}