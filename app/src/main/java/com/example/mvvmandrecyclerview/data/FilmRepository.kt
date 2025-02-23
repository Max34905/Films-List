package com.example.mvvmandrecyclerview.data

import com.example.mvvmandrecyclerview.R
import com.example.mvvmandrecyclerview.model.Film
import com.example.mvvmandrecyclerview.model.Genre
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FilmRepository @Inject constructor() {
    private var filmList: List<Film>

    init {
        filmList = listOf(
            Film(0, R.drawable.guardians_of_the_galaxy, R.string.guardians_image_description, R.string.guardians_title, R.string.guardians_description, Genre.SUPER_HERO),
            Film(1, R.drawable.shawshank, R.string.shawshank_image_description, R.string.shawshank_title, R.string.shawshank_description, Genre.DRAMA),
            Film(2, R.drawable.the_godfather, R.string.godfather_image_description, R.string.godfather_title, R.string.godfather_description, Genre.DRAMA),
            Film(3, R.drawable.the_batman, R.string.batman_image_description, R.string.batman_title, R.string.batman_description, Genre.SUPER_HERO),
            Film(4, R.drawable.the_mask, R.string.mask_image_description, R.string.mask_title, R.string.mask_description, Genre.COMEDY),
            Film(5, R.drawable.venom, R.string.venom_image_description, R.string.venom_title, R.string.venom_description, Genre.SUPER_HERO),
            Film(6, R.drawable.bohemian_rhapsody, R.string.bohemian_rhapsody_image_description, R.string.bohemian_rhapsody_title, R.string.bohemian_rhapsody_description, Genre.BIOPIC)
        )
    }

    suspend fun getFilms(): Flow<List<Film>> {
        return flow {
            emit(filmList)
        }
    }
}