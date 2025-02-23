package com.example.mvvmandrecyclerview.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mvvmandrecyclerview.R
import com.example.mvvmandrecyclerview.data.FilmRepository
import com.example.mvvmandrecyclerview.model.Film
import com.example.mvvmandrecyclerview.model.Genre

@Composable
fun FilmDetailsScreenWithViewModel(
    filmId: Int,
    viewModel: FilmsDetailsScreenViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val film by viewModel.filmStateFlow.collectAsState()

    LaunchedEffect(filmId) {
        viewModel.fetchFilmDetails(filmId)
    }
    FilmDetailsScreen(film = film, modifier = modifier)
}

@Composable
private fun FilmDetailsScreen(
    film: Film?,
    modifier: Modifier = Modifier
) {

    if (film != null) {
        Column {
            Card (
                modifier = Modifier
                    .height(300.dp)
                    .padding(8.dp),
                colors = CardDefaults.cardColors(Color.Gray)
            ) {
                Image(
                    painter = painterResource(film!!.filmImage),
                    contentDescription = stringResource(film!!.imageDescription),
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .aspectRatio(9f / 16f)
                        .padding(8.dp)
                )
            }
            Text(
                text = stringResource(film!!.filmDescription),
                fontSize = 16.sp,
                modifier = Modifier.padding(horizontal = 8.dp))
        }
    } else {
        Text(
            text = stringResource(R.string.no_data_text),
            fontSize = 16.sp
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun FilmDetailsScreenPreview() {
    FilmDetailsScreen(film = Film(1, R.drawable.shawshank, R.string.shawshank_image_description, R.string.shawshank_title, R.string.shawshank_description, Genre.DRAMA))
}