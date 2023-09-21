package com.example.movie.domain.usecases

import com.example.movie.domain.enitity.MovieEntity
import com.example.movie.domain.repositoty.MovieRepository
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(private val movieRepository: MovieRepository){

    suspend fun getMovieUseCase(id: Int): MovieEntity {
        return movieRepository.getMovie(id)
    }


}