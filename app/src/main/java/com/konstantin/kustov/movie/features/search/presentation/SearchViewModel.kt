package com.konstantin.kustov.movie.features.search.presentation

import androidx.lifecycle.MutableLiveData
import com.konstantin.kustov.movie.core.platform.BaseViewModel
import com.konstantin.kustov.movie.features.search.domain.SetSearchOptions
import javax.inject.Inject

class SearchViewModel
@Inject constructor(
    private val setSearchOptions: SetSearchOptions
) : BaseViewModel() {

    var settingSearchOptions: MutableLiveData<Boolean> = MutableLiveData()

    fun updateSearchOptions(searchOptions: SearchOptionsModel) {
        val type: String = when (searchOptions.type) {
            SearchFragment.SEARCH_TYPE_MOVIES -> SearchFragment.SEARCH_TYPE_MOVIES
            SearchFragment.SEARCH_TYPE_SERIES -> SearchFragment.SEARCH_TYPE_SERIES
            else -> ""
        }
        setSearchOptions(SetSearchOptions.Params(searchOptions.name, type)) { it ->
            it.fold(
                { handleFailure(it) },
                { handleUpdatedSearchOptions(it) })
        }

    }

    private fun handleUpdatedSearchOptions(it: Boolean) {
        settingSearchOptions.value = it
    }
}