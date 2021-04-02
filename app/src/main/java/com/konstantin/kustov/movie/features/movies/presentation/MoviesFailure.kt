package com.konstantin.kustov.movie.features.movies.presentation

import com.konstantin.kustov.movie.core.exception.Failure

class MoviesFailure {
    class MoviesNotAvailable : Failure.FeatureFailure()
}