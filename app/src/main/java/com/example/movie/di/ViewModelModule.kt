package com.example.movie.di

import androidx.lifecycle.ViewModel
import com.example.movie.presentation.viewModel.FavouriteMovieViewModel
import com.example.movie.presentation.viewModel.MovieDetailViewModel
import com.example.movie.presentation.viewModel.MovieViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    fun bindMovieViewModel(viewModel: MovieViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailViewModel::class)
    fun bindMovieDetailViewModel(viewModel: MovieDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavouriteMovieViewModel::class)
    fun bindFavouriteMovieViewModel(viewModel: FavouriteMovieViewModel): ViewModel
}