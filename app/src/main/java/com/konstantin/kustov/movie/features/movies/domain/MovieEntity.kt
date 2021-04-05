package com.konstantin.kustov.movie.features.movies.domain

import com.konstantin.kustov.movie.core.extension.empty

data class MovieEntity(
    internal val title: String,
    internal val imdbId: String,
    internal val poster: String
) {
    companion object {
        val empty = MovieEntity(String.empty(), String.empty(), String.empty())
    }
}