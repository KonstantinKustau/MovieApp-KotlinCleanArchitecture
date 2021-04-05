package com.konstantin.kustov.movie.features.details.data

import com.konstantin.kustov.movie.features.details.domain.MovieDetailsEntity

data class Details(
    private val Actors: String,
    private val Genre: String,
    private val Plot: String,
    private val Poster: String,
    private val Runtime: String,
    private val Title: String,
    private val Type: String,
    private val Year: String,
    private val imdbID: String
) {
    fun toDetails() = MovieDetailsEntity(
        Actors,
        Genre,
        Plot,
        Poster,
        Runtime,
        Title,
        Type,
        Year,
        imdbID
    )
}