package com.konstantin.kustov.movie.features.movies.data

import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesService
@Inject constructor(retrofit: Retrofit) : MovieApi {
    private val moviesApi by lazy { retrofit.create(MovieApi::class.java) }

    override fun getMovies(title: String, type: String): Call<Movies> {
        return moviesApi.getMovies(title, type)
    }
}
