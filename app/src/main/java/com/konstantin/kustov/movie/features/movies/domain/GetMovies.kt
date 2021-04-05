package com.konstantin.kustov.movie.features.movies.domain

import com.konstantin.kustov.movie.core.exception.Failure
import com.konstantin.kustov.movie.core.functional.Either
import com.konstantin.kustov.movie.core.interactor.UseCase
import javax.inject.Inject

class GetMovies
@Inject constructor(private val moviesRepository: MoviesRepository) :
    UseCase<List<MovieEntity>, GetMovies.Params>() {

    data class Params(val searchLine: String, val searchType: String)

    override suspend fun run(params: Params): Either<Failure, List<MovieEntity>> =
        moviesRepository.getMovies(params.searchLine, params.searchType)

}