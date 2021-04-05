package com.konstantin.kustov.movie.features.details.domain

import com.konstantin.kustov.movie.core.extension.empty

data class MovieDetailsEntity(
    internal val actors: String,
    internal val genre: String,
    internal val plot: String,
    internal val poster: String,
    internal val runtime: String,
    internal val title: String,
    internal val type: String,
    internal val year: String,
    internal val imdbID: String
) {
    companion object {
        val empty = MovieDetailsEntity(
            String.empty(), String.empty(), String.empty(), String.empty(),
            String.empty(), String.empty(), String.empty(), String.empty(), String.empty()
        )
    }
}