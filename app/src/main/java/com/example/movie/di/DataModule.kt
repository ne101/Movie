package com.example.movie.di

import android.app.Application
import com.example.movie.data.reposImpl.MovieRepositoryImpl
import com.example.movie.data.database.AppDataBase
import com.example.movie.data.database.MovieListDao
import com.example.movie.data.network.api.ApiFactory
import com.example.movie.data.network.api.ApiService
import com.example.movie.domain.repositoty.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindMovieRepository(impl: MovieRepositoryImpl): MovieRepository

    companion object {

        @ApplicationScope
        @Provides
        fun provideApiService(): ApiService = ApiFactory.apiService

        @ApplicationScope
        @Provides
        fun provideMovieDao(application: Application): MovieListDao {
            return AppDataBase.getInstance(application).movieListDao()
        }
    }
}