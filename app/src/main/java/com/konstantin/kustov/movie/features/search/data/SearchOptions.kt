package com.konstantin.kustov.movie.features.search.data

import com.konstantin.kustov.movie.features.search.domain.SearchOptionsEntity

data class SearchOptions(
    private val name: String,
    private val type: String
) {
    fun toSearchOptions() = SearchOptionsEntity(
            name,
            type
    )
}