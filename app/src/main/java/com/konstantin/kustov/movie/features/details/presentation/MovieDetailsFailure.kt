package com.konstantin.kustov.movie.features.details.presentation

import com.konstantin.kustov.movie.core.exception.Failure

class MovieDetailsFailure {
    class MovieDetailsNotAvailable : Failure.FeatureFailure()
}