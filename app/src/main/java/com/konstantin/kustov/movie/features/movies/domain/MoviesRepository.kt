package com.konstantin.kustov.movie.features.movies.domain

import com.konstantin.kustov.movie.core.exception.Failure
import com.konstantin.kustov.movie.core.functional.Either

interface MoviesRepository {

    fun getMovies(searchLine: String, searchType: String): Either<Failure, List<MovieEntity>>
}