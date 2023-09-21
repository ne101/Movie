package com.example.movie.domain.enitity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieEntity(
    val movieName: String,
    val poster: String,
    val description: String,
    val year: String,
    val rating: String,
    var movieId: Int = UNDEFINED_ID
): Parcelable {
    companion object {
        const val UNDEFINED_ID = 0
    }
}


