package com.example.movie.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("favourite_movies_table")
data class MovieDBModel(
    @PrimaryKey
    val movieId: Int,
    val movieName: String,
    val poster: String,
    val description: String,
    val year: String,
    val rating: String
)
