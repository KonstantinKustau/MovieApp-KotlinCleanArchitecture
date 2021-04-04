package com.konstantin.kustov.movie.features.search.presentation

import com.konstantin.kustov.movie.core.exception.Failure

class SearchFailure {
    class OptionsCouldNotBeSaved : Failure.FeatureFailure()
    class SearchFieldIsEmpty   : Failure.FeatureFailure()
}