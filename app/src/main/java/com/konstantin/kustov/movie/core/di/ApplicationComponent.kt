package com.konstantin.kustov.movie.core.di

import com.konstantin.kustov.movie.AndroidApplication
import com.konstantin.kustov.movie.core.di.viewmodel.ViewModelModule
import com.konstantin.kustov.movie.core.navigation.RouteActivity
import com.konstantin.kustov.movie.features.details.presentation.MovieDetailsFragment
import com.konstantin.kustov.movie.features.movies.presentation.MoviesFragment
import com.konstantin.kustov.movie.features.search.presentation.SearchFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(application: AndroidApplication)
    fun inject(routeActivity: RouteActivity)

    fun inject(searchFragment: SearchFragment)
    fun inject(moviesFragment: MoviesFragment)
    fun inject(moviesDetailsFragment: MovieDetailsFragment)
}
