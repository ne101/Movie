package com.example.movie.data.network.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("docs")
    val movies: List<Movie>
)
