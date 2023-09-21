package com.example.movie.domain.repositoty

import androidx.lifecycle.LiveData
import com.example.movie.domain.enitity.MovieEntity

interface MovieRepository {
    fun getMovies(page: Int): LiveData<List<MovieEntity>>
    suspend fun getMovie(id: Int): MovieEntity
    fun getFavouriteMovies(): LiveData<List<MovieEntity>>
    suspend fun getFavouriteMovie(id: Int): MovieEntity
    suspend fun removeFavouriteMovie(movieEntity: MovieEntity)
    suspend fun addFavouriteMovie(movieEntity: MovieEntity)
}