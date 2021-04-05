package com.konstantin.kustov.movie.features.details.domain

data class MovieDetailsEntity(
    val actors: String,
    val genre: String,
    val plot: String,
    val poster: String,
    val runtime: String,
    val title: String,
    val type: String,
    val year: String,
    val imdbID: String
)