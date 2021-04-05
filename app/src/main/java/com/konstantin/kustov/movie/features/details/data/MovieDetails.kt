package com.konstantin.kustov.movie.features.details.data

import com.konstantin.kustov.movie.core.extension.empty
import com.konstantin.kustov.movie.features.details.domain.MovieDetailsEntity

data class Details(
    val Actors: String,
    val Genre: String,
    val Plot: String,
    val Poster: String,
    val Runtime: String,
    val Title: String,
    val Type: String,
    val Year: String,
    val imdbID: String
) {

    companion object {
        val empty = Details(
            String.empty(), String.empty(), String.empty(), String.empty(),
            String.empty(), String.empty(), String.empty(), String.empty(), String.empty()
        )
    }

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