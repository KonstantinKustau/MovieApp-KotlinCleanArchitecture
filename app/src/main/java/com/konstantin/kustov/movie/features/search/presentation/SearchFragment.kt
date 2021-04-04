package com.konstantin.kustov.movie.features.search.presentation

import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import androidx.annotation.StringRes
import androidx.core.widget.addTextChangedListener
import com.konstantin.kustov.movie.R
import com.konstantin.kustov.movie.core.exception.Failure
import com.konstantin.kustov.movie.core.extension.appContext
import com.konstantin.kustov.movie.core.extension.failure
import com.konstantin.kustov.movie.core.extension.observe
import com.konstantin.kustov.movie.core.extension.viewModel
import com.konstantin.kustov.movie.core.navigation.Navigator
import com.konstantin.kustov.movie.core.platform.BaseFragment
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject


class SearchFragment : BaseFragment() {

    companion object {
        const val SEARCH_TYPE_MOVIES: String = "movies"
        const val SEARCH_TYPE_SERIES: String = "series"
    }

    private var searchLine: String = ""

    private var searchType: String = ""

    @Inject
    lateinit var navigator: Navigator

    private lateinit var searchViewModel: SearchViewModel

    override fun layoutId() = R.layout.fragment_search

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        setHasOptionsMenu(true)

        searchViewModel = viewModel(viewModelFactory) {
            observe(settingSearchOptions, ::handleSearchOptionsUpdate)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
    }

    private fun initializeView() {
        searchEditText.addTextChangedListener { text -> searchLine = text.toString() }
        searchRadioGroup.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { _, checkedId ->
            run {
                when (checkedId) {
                    R.id.radioAll -> searchType = ""
                    R.id.radioMovies -> searchType = SEARCH_TYPE_MOVIES
                    R.id.radioSeries -> searchType = SEARCH_TYPE_SERIES
                }
            }

        })
        searchButton.setOnClickListener {
            if (searchLine != "") {
                saveSearchOptions()
            } else {
                handleFailure(SearchFailure.SearchFieldIsEmpty())
            }
        }
    }

    private fun saveSearchOptions() {
        showProgress()
        val searchOptions = SearchOptionsModel(searchLine, searchType)
        searchViewModel.updateSearchOptions(searchOptions)
    }

    private fun handleSearchOptionsUpdate(it: Boolean?) {
        if (it != null) {
            if (it) {
                navigator.showMovies(appContext, searchLine, searchType)
            } else {
                handleFailure(SearchFailure.OptionsCouldNotBeSaved())
            }
        } else {
            handleFailure(Failure.UnknownError)
        }
        hideProgress()
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is SearchFailure.OptionsCouldNotBeSaved -> renderFailure(R.string.failure_saving_options_error)
            is SearchFailure.SearchFieldIsEmpty -> renderFailure(R.string.failure_search_is_empty_error)
            else -> renderFailure(R.string.failure_unknown_error)
        }
    }

    private fun renderFailure(@StringRes message: Int) {
        hideProgress()
        notify(message)
    }
}