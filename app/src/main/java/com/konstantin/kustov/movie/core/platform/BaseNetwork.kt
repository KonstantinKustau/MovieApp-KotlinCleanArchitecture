package com.konstantin.kustov.movie.core.platform

import com.konstantin.kustov.movie.core.exception.Failure
import com.konstantin.kustov.movie.core.functional.Either
import com.konstantin.kustov.movie.features.movies.data.Movies
import retrofit2.Call

abstract class BaseNetwork {
    internal fun <T, R> request(
        call: Call<T>,
        transform: (T) -> R,
        default: T
    ): Either<Failure, R> {
        return try {
            val response = call.execute()
            when (response.isSuccessful) {
                true -> {
                    if ((response.body() as Movies).Search.isNullOrEmpty()) {
                        Either.Right(transform(default))
                    } else {
                        Either.Right(transform((response.body() ?: default)))

                    }
                }
                false -> Either.Left(Failure.ServerError)
            }
        } catch (exception: Throwable) {
            Either.Left(Failure.ServerError)
        }
    }
}