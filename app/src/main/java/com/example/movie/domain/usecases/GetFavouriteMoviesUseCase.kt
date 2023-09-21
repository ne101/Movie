package com.example.movie.domain.usecases

import androidx.lifecycle.LiveData
import com.example.movie.domain.enitity.MovieEntity
import com.example.movie.domain.repositoty.MovieRepository
import javax.inject.Inject

class GetFavouriteMoviesUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    fun getFavouriteMovies(): LiveData<List<MovieEntity>> {
        return movieRepository.getFavouriteMovies()
    }
}