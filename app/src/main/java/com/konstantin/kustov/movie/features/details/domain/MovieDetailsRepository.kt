package com.konstantin.kustov.movie.features.details.domain

import com.konstantin.kustov.movie.core.exception.Failure
import com.konstantin.kustov.movie.core.functional.Either

interface MovieDetailsRepository {

    fun getMovieDetails(imdb_id: String): Either<Failure, MovieDetailsEntity>
}