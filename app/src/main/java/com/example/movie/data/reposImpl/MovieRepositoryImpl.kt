package com.example.movie.data.reposImpl

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.example.movie.data.database.MovieListDao
import com.example.movie.data.mapper.MoviesMapper
import com.example.movie.data.network.api.ApiService
import com.example.movie.domain.enitity.MovieEntity
import com.example.movie.domain.repositoty.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val mapper: MoviesMapper,
    private val movieListDao: MovieListDao
) : MovieRepository {


    override fun getMovies(page: Int): LiveData<List<MovieEntity>> {
        return liveData {
            val response = apiService.loadMovies(page)
            val movies = mapper.networkModelListToEntityList(response.movies)
            emit(movies)
        }
    }

    override suspend fun getMovie(id: Int): MovieEntity {
        val response = apiService.loadMovie(id)
        return mapper.networkModelToEntity(response)
    }

    override fun getFavouriteMovies(): LiveData<List<MovieEntity>> =
        movieListDao.getFavouriteMovieList().map {
            mapper.databaseListModelToEntityList(it)
        }

    override suspend fun getFavouriteMovie(id: Int): MovieEntity {
        return mapper.databaseModelToEntity(movieListDao.getMovie(id))
    }

    override suspend fun removeFavouriteMovie(movieEntity: MovieEntity) {
        return movieListDao.deleteMovie(movieEntity.movieId)
    }

    override suspend fun addFavouriteMovie(movieEntity: MovieEntity) {
        return movieListDao.insertMovie(mapper.entityToDatabaseModel(movieEntity))
    }
}

