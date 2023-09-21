package com.example.movie.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.movie.domain.enitity.MovieEntity
import com.example.movie.domain.usecases.GetMoviesUseCase
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
) : ViewModel() {

    var page = 0

    private val _movies = MutableLiveData<List<MovieEntity>>()
    val movies: LiveData<List<MovieEntity>>
        get() = _movies
    private val _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean>
        get() = _progress
    private lateinit var moviesObserver: Observer<List<MovieEntity>>


    init {
        loadMoreMovies()
    }

    fun loadMoreMovies() {
        val isLoading = _progress.value
        if (isLoading != null && isLoading) {
            return
        }
        page++
        _progress.value = true
        moviesObserver = Observer {
            val currentMovies = _movies.value ?: emptyList()
            val updatedMovies = currentMovies + it
            _movies.value = updatedMovies
            _progress.value = false
        }
        getMoviesUseCase.getMovies(page).observeForever(moviesObserver)
    }


    override fun onCleared() {
        super.onCleared()
        getMoviesUseCase.getMovies(page).removeObserver(moviesObserver)
    }
}

