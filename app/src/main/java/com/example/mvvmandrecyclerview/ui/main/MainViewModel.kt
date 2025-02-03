package com.example.mvvmandrecyclerview.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.mvvmandrecyclerview.data.FilmRepository
import com.example.mvvmandrecyclerview.model.Film
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (
    private val repository: FilmRepository
) : ViewModel() {
    private val _filmListMutableStateFlow = MutableStateFlow<List<Film>>(emptyList())

    val filmListStateFlow: StateFlow<List<Film>> = _filmListMutableStateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getFilms().collect { films ->
                _filmListMutableStateFlow.value = films
            }
        }
    }
}