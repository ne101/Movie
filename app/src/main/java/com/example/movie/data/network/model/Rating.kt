package com.example.movie.data.network.model

import com.google.gson.annotations.SerializedName

data class Rating(
    @SerializedName("kp")
    val rating: String
)
