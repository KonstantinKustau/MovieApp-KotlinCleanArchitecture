package com.konstantin.kustov.movie.features.search.presentation

import androidx.lifecycle.MutableLiveData
import com.konstantin.kustov.movie.core.platform.BaseViewModel
import javax.inject.Inject

class SearchViewModel
@Inject constructor(
) : BaseViewModel() {

    var searchOptions: MutableLiveData<SearchOptionsModel> = MutableLiveData()

    fun updateSearchOptions(searchOptions: SearchOptionsModel) {
    }

    private fun handleUpdateSearchOptions(searchOptions: SearchOptionsModel) {
    }
}