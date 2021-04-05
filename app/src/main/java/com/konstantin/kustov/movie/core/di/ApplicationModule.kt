package com.konstantin.kustov.movie.core.di

import android.content.Context
import com.konstantin.kustov.movie.AndroidApplication
import com.konstantin.kustov.movie.BuildConfig
import com.konstantin.kustov.movie.features.search.data.SearchRepositoryImpl
import com.konstantin.kustov.movie.features.search.domain.SearchRepository
import com.konstantin.kustov.movie.features.movies.data.MoviesRepositoryImpl
import com.konstantin.kustov.movie.features.movies.domain.MoviesRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: AndroidApplication) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = application

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://www.omdbapi.com/")
            .client(createClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun createClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }
        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideSearchRepository(dataSource: SearchRepositoryImpl): SearchRepository = dataSource

    @Provides
    @Singleton
    fun provideMovieRepository(dataSource: MoviesRepositoryImpl): MoviesRepository = dataSource

}
