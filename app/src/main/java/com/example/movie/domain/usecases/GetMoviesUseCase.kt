package com.example.movie.domain.usecases

import androidx.lifecycle.LiveData
import com.example.movie.domain.enitity.MovieEntity
import com.example.movie.domain.repositoty.MovieRepository
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    fun getMovies(page: Int): LiveData<List<MovieEntity>> {
        return movieRepository.getMovies(page)
    }
}