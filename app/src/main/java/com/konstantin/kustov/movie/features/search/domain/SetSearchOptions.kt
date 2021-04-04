package com.konstantin.kustov.movie.features.search.domain

import com.konstantin.kustov.movie.core.exception.Failure
import com.konstantin.kustov.movie.core.functional.Either
import com.konstantin.kustov.movie.core.interactor.UseCase
import javax.inject.Inject

class SetSearchOptions
@Inject constructor(private val searchRepository: SearchRepository) :
    UseCase<Boolean, SetSearchOptions.Params>() {

    data class Params(val name: String, val type: String)

    override suspend fun run(params: Params): Either<Failure, Boolean> {
        return searchRepository.setSearchOptions(params.name, params.type)
    }

}