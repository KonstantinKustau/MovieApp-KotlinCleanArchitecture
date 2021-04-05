package com.konstantin.kustov.movie.features.movies.data

import com.konstantin.kustov.movie.core.extension.empty
import com.konstantin.kustov.movie.features.movies.domain.MovieEntity

data class Movies(
    val Response: String,
    val Search: List<Search>,
    val totalResults: String
) {
    companion object {
        val empty = Movies(String.empty(), emptyList(), String.empty())
    }
}

data class Search(
    val Poster: String,
    val Title: String,
    val Type: String,
    val Year: String,
    val imdbID: String
) {
    fun toMovie() = MovieEntity(
        Title,
        imdbID,
        Poster
    )
}