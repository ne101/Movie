package com.example.movie.domain.usecases

import com.example.movie.domain.enitity.MovieEntity
import com.example.movie.domain.repositoty.MovieRepository
import javax.inject.Inject

class RemoveFavouriteMovieUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    suspend fun removeFavouriteMovie(movieEntity: MovieEntity) {
        movieRepository.removeFavouriteMovie(movieEntity)
    }
}