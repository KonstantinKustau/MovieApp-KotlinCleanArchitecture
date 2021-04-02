package com.konstantin.kustov.movie.features.search.presentation

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.konstantin.kustov.movie.R
import com.konstantin.kustov.movie.core.exception.Failure
import com.konstantin.kustov.movie.core.extension.observe
import com.konstantin.kustov.movie.core.extension.failure
import com.konstantin.kustov.movie.core.extension.viewModel
import com.konstantin.kustov.movie.core.navigation.Navigator
import com.konstantin.kustov.movie.core.platform.BaseFragment
import javax.inject.Inject

class SearchFragment : BaseFragment() {

    @Inject
    lateinit var navigator: Navigator

    private lateinit var searchViewModel: SearchViewModel

    override fun layoutId() = R.layout.fragment_search

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        setHasOptionsMenu(true)

        searchViewModel = viewModel(viewModelFactory) {
            observe(searchOptions, ::handleSuccess)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
        saveSearchOptions()
    }

    private fun initializeView() {
    }

    private fun saveSearchOptions() {
        showProgress()
        val searchOptions = SearchOptionsModel("test", MovieType.All)
        searchViewModel.updateSearchOptions(searchOptions)
    }

    private fun handleSuccess(searchOptions: SearchOptionsModel?) {
        hideProgress()
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> renderFailure(R.string.failure_network_connection)
            is Failure.ServerError -> renderFailure(R.string.failure_server_error)
            else -> renderFailure(R.string.failure_unknown_error)
        }
    }

    private fun renderFailure(@StringRes message: Int) {
        hideProgress()
        notifyWithAction(message, R.string.failure_action_refresh, ::saveSearchOptions)
    }
}