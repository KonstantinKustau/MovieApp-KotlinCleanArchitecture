package com.konstantin.kustov.movie.features.details.data

import com.konstantin.kustov.movie.core.exception.Failure
import com.konstantin.kustov.movie.core.functional.Either
import com.konstantin.kustov.movie.core.platform.BaseNetwork
import com.konstantin.kustov.movie.core.platform.NetworkHandler
import com.konstantin.kustov.movie.features.details.domain.MovieDetailsEntity
import com.konstantin.kustov.movie.features.details.domain.MovieDetailsRepository
import javax.inject.Inject

class MovieDetailsRepositoryImpl
@Inject constructor(
    private val networkHandler: NetworkHandler,
    private val service: MovieDetailsService
) : MovieDetailsRepository, BaseNetwork() {

    override fun getMovieDetails(imdb_id: String): Either<Failure, MovieDetailsEntity> {
        return when (networkHandler.isConnected) {
            true -> request(
                service.getMovieDetails(imdb_id),
                { details -> details.toDetails() },
                Details("N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A")
            )
            false, null -> Either.Left(
                Failure.NetworkConnection
            )
        }
    }
}