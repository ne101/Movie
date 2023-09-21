package com.example.movie.presentation

import android.app.Application
import com.example.movie.di.DaggerApplicationComponent

class MovieApplication : Application(){

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}