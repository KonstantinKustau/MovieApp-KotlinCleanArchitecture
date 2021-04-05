package com.konstantin.kustov.movie.features.details.domain

import com.konstantin.kustov.movie.core.exception.Failure
import com.konstantin.kustov.movie.core.functional.Either
import com.konstantin.kustov.movie.core.interactor.UseCase
import javax.inject.Inject

class GetMovieDetails
@Inject constructor(private val movieDetailsRepository: MovieDetailsRepository) :
    UseCase<MovieDetailsEntity, GetMovieDetails.Params>() {

    data class Params(val imdb_id: String)

    override suspend fun run(params: Params): Either<Failure, MovieDetailsEntity> =
        movieDetailsRepository.getMovieDetails(params.imdb_id)

}