package com.example.mvvmandrecyclerview.di

import com.example.mvvmandrecyclerview.data.FilmRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object FilmRepositoryModule {

    @Provides
    fun provideFilmRepository(): FilmRepository {
        return FilmRepository()
    }
}