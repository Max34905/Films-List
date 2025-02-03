package com.example.mvvmandrecyclerview.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmandrecyclerview.model.Film
import com.example.mvvmandrecyclerview.data.FilmRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmsDetailsViewModel @Inject constructor (
    private val repository: FilmRepository
) : ViewModel() {
    private val _filmMutableStateFlow = MutableStateFlow<Film?>(null)

    val filmStateFlow: StateFlow<Film?> = _filmMutableStateFlow.asStateFlow()

    fun fetchFilmDetails(id: Int) {
        viewModelScope.launch {
            repository.getFilms()
                .map {films -> films.find { it.id == id }}
                .collect{film -> _filmMutableStateFlow.value = film}
        }
    }
}