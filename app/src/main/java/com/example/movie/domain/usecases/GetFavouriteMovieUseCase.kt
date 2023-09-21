package com.example.movie.domain.usecases

import androidx.lifecycle.LiveData
import com.example.movie.domain.enitity.MovieEntity
import com.example.movie.domain.repositoty.MovieRepository
import javax.inject.Inject

class GetFavouriteMovieUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    suspend fun getFavouriteMovie(id: Int): MovieEntity {
        return movieRepository.getFavouriteMovie(id)
    }
}