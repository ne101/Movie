package com.example.movie.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movie.domain.enitity.MovieEntity
import com.example.movie.domain.usecases.AddFavouriteMovieUseCase
import com.example.movie.domain.usecases.GetFavouriteMoviesUseCase
import com.example.movie.domain.usecases.GetMovieUseCase
import com.example.movie.domain.usecases.RemoveFavouriteMovieUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    private val getMovieUseCase: GetMovieUseCase,
    private val addFavouriteMovieUseCase: AddFavouriteMovieUseCase,
    private val removeFavouriteMovieUseCase: RemoveFavouriteMovieUseCase,
    private val getFavouriteMoviesUseCase: GetFavouriteMoviesUseCase
) :
    ViewModel() {

    private val scope = CoroutineScope(Dispatchers.IO)

    private val _movie = MutableLiveData<MovieEntity>()
    val movie: LiveData<MovieEntity>
        get() = _movie

    val favouriteMovies = getFavouriteMoviesUseCase.getFavouriteMovies()


    fun loadDetailInfo(id: Int) {
       scope.launch {
            val movie = getMovieUseCase.getMovieUseCase(id)
            _movie.postValue(movie)
        }
    }



    fun addMovieToDataBase(movieEntity: MovieEntity) {
        scope.launch {
            addFavouriteMovieUseCase.addFavouriteMovie(movieEntity)
        }
    }

    fun removeMovieFromDataBase(movieEntity: MovieEntity) {
        scope.launch {
            removeFavouriteMovieUseCase.removeFavouriteMovie(movieEntity)
        }
    }
}