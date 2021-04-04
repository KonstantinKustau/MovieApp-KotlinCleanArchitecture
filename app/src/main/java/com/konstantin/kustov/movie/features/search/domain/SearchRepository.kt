package com.konstantin.kustov.movie.features.search.domain

import com.konstantin.kustov.movie.core.exception.Failure
import com.konstantin.kustov.movie.core.functional.Either

interface SearchRepository {

    fun setSearchOptions(name: String, type: String): Either<Failure, Boolean>
}