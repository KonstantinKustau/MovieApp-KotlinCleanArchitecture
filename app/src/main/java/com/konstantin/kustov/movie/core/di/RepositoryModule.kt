package com.konstantin.kustov.movie.core.di

import com.konstantin.kustov.movie.features.details.data.MovieDetailsRepositoryImpl
import com.konstantin.kustov.movie.features.details.domain.MovieDetailsRepository
import com.konstantin.kustov.movie.features.movies.data.MoviesRepositoryImpl
import com.konstantin.kustov.movie.features.movies.domain.MoviesRepository
import com.konstantin.kustov.movie.features.search.data.SearchRepositoryImpl
import com.konstantin.kustov.movie.features.search.domain.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideSearchRepository(dataSource: SearchRepositoryImpl): SearchRepository = dataSource

    @Provides
    @Singleton
    fun provideMovieRepository(dataSource: MoviesRepositoryImpl): MoviesRepository = dataSource

    @Provides
    @Singleton
    fun provideMovieDetailsRepository(dataSource: MovieDetailsRepositoryImpl): MovieDetailsRepository =
        dataSource

}