package com.konstantin.kustov.movie.features.search.data

import com.konstantin.kustov.movie.core.exception.Failure
import com.konstantin.kustov.movie.core.functional.Either
import com.konstantin.kustov.movie.core.storage.PreferencesStorage
import com.konstantin.kustov.movie.features.search.domain.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl
@Inject constructor(
    private val preferencesStorage: PreferencesStorage
) : SearchRepository {

    override fun setSearchOptions(
        name: String,
        type: String
    ): Either<Failure, Boolean> {
        return if (preferencesStorage.setSearchName(name) && preferencesStorage.setSearchType(type)) {
            Either.Right(true)
        } else {
            Either.Right(false)
        }
    }
}