package com.konstantin.kustov.movie.features.details.presentation

data class MovieDetailsModel(
        val title: String,
        val plot: String,
        val genre: String,
        val actors: String,
        val year: String,
        val type: String,
        val runtime: String,
        val poster: String)