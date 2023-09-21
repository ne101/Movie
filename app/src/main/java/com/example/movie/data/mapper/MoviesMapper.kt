package com.example.movie.data.mapper

import com.example.movie.data.database.MovieDBModel
import com.example.movie.data.network.model.Movie
import com.example.movie.data.network.model.Poster
import com.example.movie.data.network.model.Rating
import com.example.movie.domain.enitity.MovieEntity
import javax.inject.Inject

class MoviesMapper @Inject constructor() {

    fun entityToNetworkModel(movieEntity: MovieEntity): Movie = Movie(
        id = movieEntity.movieId,
        name = movieEntity.movieName,
        description = movieEntity.description,
        year = movieEntity.year,
        poster = Poster(movieEntity.poster),
        rating = Rating(movieEntity.rating)
    )

    fun networkModelToEntity(movie: Movie): MovieEntity = MovieEntity(
        movieId = movie.id,
        movieName = movie.name,
        description = movie.description,
        year = movie.year,
        poster = movie.poster.url,
        rating = movie.rating.rating
    )

    fun databaseModelToEntity(movieDBModel: MovieDBModel): MovieEntity = MovieEntity(
        movieId = movieDBModel.movieId,
        movieName = movieDBModel.movieName,
        description = movieDBModel.description,
        year = movieDBModel.year,
        poster = movieDBModel.poster,
        rating = movieDBModel.rating
    )

    fun databaseListModelToEntityList(list: List<MovieDBModel>) = list.map {
        databaseModelToEntity(it)
    }

    fun entityToDatabaseModel(movieEntity: MovieEntity): MovieDBModel = MovieDBModel(
        movieName = movieEntity.movieName,
        movieId = movieEntity.movieId,
        description = movieEntity.description,
        year = movieEntity.year,
        poster = movieEntity.poster,
        rating = movieEntity.rating
    )

    fun networkModelListToEntityList(list: List<Movie>) = list.map {
        networkModelToEntity(it)
    }
}