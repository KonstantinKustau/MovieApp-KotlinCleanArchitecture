package com.konstantin.kustov.movie.features.details.data

import com.konstantin.kustov.movie.BuildConfig
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

internal interface MovieDetailsApi {
    companion object {
        private const val PARAM_IMDb_ID = "i"
    }

    @GET("?apikey=" + BuildConfig.API_KEY)
    fun getMovieDetails(
        @Query(PARAM_IMDb_ID) imdb_id: String
    ): Call<Details>
}
