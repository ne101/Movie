package com.example.movie.data.network.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("year")
    val year: String,
    @SerializedName("poster")
    val poster: Poster,
    @SerializedName("rating")
    val rating: Rating
)

