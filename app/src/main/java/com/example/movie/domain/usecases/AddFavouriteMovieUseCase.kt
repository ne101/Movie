package com.example.movie.domain.usecases

import com.example.movie.domain.enitity.MovieEntity
import com.example.movie.domain.repositoty.MovieRepository
import javax.inject.Inject

class AddFavouriteMovieUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    suspend fun addFavouriteMovie(movieEntity: MovieEntity) {
        movieRepository.addFavouriteMovie(movieEntity)
    }
}