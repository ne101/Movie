package com.example.movie.di

import android.app.Application
import com.example.movie.presentation.fragments.FavouriteMovieFragment
import com.example.movie.presentation.fragments.MovieDetailFragment
import com.example.movie.presentation.fragments.MovieFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [
    DataModule::class,
    ViewModelModule::class
])
interface ApplicationComponent {

    fun inject(fragment: MovieFragment)
    fun inject(fragment: MovieDetailFragment)
    fun inject(fragment: FavouriteMovieFragment)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): ApplicationComponent
    }

}