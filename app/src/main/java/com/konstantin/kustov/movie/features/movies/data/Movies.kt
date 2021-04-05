package com.konstantin.kustov.movie.features.movies.data

import com.konstantin.kustov.movie.features.movies.domain.MovieEntity

data class Movies(
    private val Response: String,
    internal val Search: List<Search>,
    private val totalResults: String
)

data class Search(
    private val Poster: String,
    private val Title: String,
    private val Type: String,
    private val Year: String,
    private val imdbID: String
) {
    fun toMovie() = MovieEntity(
        Title,
        imdbID,
        Poster
    )
}