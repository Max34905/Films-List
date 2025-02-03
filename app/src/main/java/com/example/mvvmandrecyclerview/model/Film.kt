package com.example.mvvmandrecyclerview.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Film(
    val id: Int,
    @DrawableRes val filmImage: Int,
    @StringRes val imageDescription: Int,
    @StringRes val filmTitle: Int,
    @StringRes val filmDescription: Int,
    val genre: Genre
)
