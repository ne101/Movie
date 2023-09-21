package com.example.movie.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface MovieListDao {

    @Query("SELECT * FROM favourite_movies_table")
    fun getFavouriteMovieList(): LiveData<List<MovieDBModel>>
    @Query("SELECT * FROM favourite_movies_table WHERE movieId=:id")
    fun getMovie(id: Int): MovieDBModel
    @Insert
    suspend fun insertMovie(movieDBModel: MovieDBModel)
    @Query("DELETE FROM favourite_movies_table WHERE movieId=:id")
    suspend fun deleteMovie(id: Int)
}