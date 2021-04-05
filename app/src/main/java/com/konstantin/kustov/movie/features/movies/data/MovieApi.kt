package com.konstantin.kustov.movie.features.movies.data

import com.konstantin.kustov.movie.BuildConfig
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

internal interface MovieApi {
    companion object {
        private const val PARAM_TITLE = "s"
        private const val PARAM_TYPE = "type"
    }

    @GET("?apikey=" + BuildConfig.API_KEY)
    fun getMovies(
        @Query(PARAM_TITLE) title: String,
        @Query(PARAM_TYPE) type: String
    ): Call<Movies>
}
