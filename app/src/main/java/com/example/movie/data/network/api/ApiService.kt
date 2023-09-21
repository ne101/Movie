package com.example.movie.data.network.api

import com.example.movie.data.network.model.Movie
import com.example.movie.data.network.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("v1.3/movie?&limit=60&token=08KV6BS-BND44Z9-H8SP4JY-5WDC7C4")
    suspend fun loadMovies(@Query("page") page: Int): MovieResponse
    @GET("v1.3/movie/{id}?&token=08KV6BS-BND44Z9-H8SP4JY-5WDC7C4")
    suspend fun loadMovie(@Path("id") id: Int): Movie
}