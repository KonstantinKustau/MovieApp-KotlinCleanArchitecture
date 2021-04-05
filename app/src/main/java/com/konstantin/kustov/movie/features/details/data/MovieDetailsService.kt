package com.konstantin.kustov.movie.features.details.data

import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieDetailsService
@Inject constructor(retrofit: Retrofit) : MovieDetailsApi {
    private val moviesApi by lazy { retrofit.create(MovieDetailsApi::class.java) }

    override fun getMovieDetails(imdb_id: String): Call<Details> {
        return moviesApi.getMovieDetails(imdb_id)
    }
}
