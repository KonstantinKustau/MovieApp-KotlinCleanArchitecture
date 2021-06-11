package com.konstantin.kustov.movie.features.movies.data

import com.konstantin.kustov.movie.core.exception.Failure
import com.konstantin.kustov.movie.core.functional.Either
import com.konstantin.kustov.movie.core.platform.NetworkHandler
import com.konstantin.kustov.movie.features.movies.domain.MovieEntity
import com.konstantin.kustov.movie.features.movies.domain.MoviesRepository
import javax.inject.Inject

class MoviesRepositoryImpl
@Inject constructor(
    private val networkHandler: NetworkHandler,
    private val service: MoviesService
) : MoviesRepository, MovieNetwork() {

    override fun getMovies(
        searchLine: String,
        searchType: String
    ): Either<Failure, List<MovieEntity>> {
        return when (networkHandler.isNetworkAvailable()) {
            true -> request(
                service.getMovies(searchLine, searchType),
                { it.Search.map { movie -> movie.toMovie() } },
                Movies.empty
            )
            false, null -> Either.Left(
                Failure.NetworkConnection
            )
        }
    }
}