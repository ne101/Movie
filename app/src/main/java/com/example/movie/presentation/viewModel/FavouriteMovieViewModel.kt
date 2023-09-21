package com.example.movie.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.movie.domain.enitity.MovieEntity
import com.example.movie.domain.usecases.GetFavouriteMoviesUseCase
import javax.inject.Inject

class FavouriteMovieViewModel @Inject constructor(
    private val getFavouriteMoviesUseCase: GetFavouriteMoviesUseCase
) : ViewModel() {
    private val _movies = getFavouriteMoviesUseCase.getFavouriteMovies()
    val movie: LiveData<List<MovieEntity>>
    get() = _movies
}