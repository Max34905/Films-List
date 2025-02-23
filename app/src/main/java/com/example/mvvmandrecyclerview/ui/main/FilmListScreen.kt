package com.example.mvvmandrecyclerview.ui.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.mvvmandrecyclerview.R
import com.example.mvvmandrecyclerview.model.Film
import com.example.mvvmandrecyclerview.model.Genre

@Composable
fun FilmListScreenWithViewModel(
    viewModel: FilmListScreenViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    navigateToDetails: (Int) -> Unit
) {
    val films by viewModel.filmListStateFlow.collectAsState()
    var isFiltered by remember { mutableStateOf(viewModel.isFiltered) }

    FilmListScreen(
        films = films,
        isFiltered = isFiltered,
        onFilterChange = {
            isFiltered = it
            viewModel.setFilterState(isFiltered)
        },
        navigateToDetails = navigateToDetails,
        modifier = modifier
    )
}

@Composable
private fun FilmListScreen(
    films: List<Film>,
    isFiltered: Boolean,
    onFilterChange: (Boolean) -> Unit,
    navigateToDetails: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {

    val displayedFilms = if (isFiltered) {
        films.filter {it.genre == Genre.SUPER_HERO}
    } else {
        films
    }

    Column {
        Row (
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.filter_switch_hint),
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 8.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Switch(
                checked = isFiltered,
                onCheckedChange = onFilterChange
            )
        }
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(displayedFilms) { film ->
                FilmCard(film = film) {
                    navigateToDetails(film.id)
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun FilmListScreenPreview() {
    val navController = rememberNavController()
    FilmListScreen(
        films = listOf(Film(0, R.drawable.guardians_of_the_galaxy, R.string.guardians_image_description, R.string.guardians_title, R.string.guardians_description, Genre.SUPER_HERO), Film(1, R.drawable.shawshank, R.string.shawshank_image_description, R.string.shawshank_title, R.string.shawshank_description, Genre.DRAMA)),
        isFiltered = false,
        onFilterChange = {},
        navigateToDetails = {}
    )
}