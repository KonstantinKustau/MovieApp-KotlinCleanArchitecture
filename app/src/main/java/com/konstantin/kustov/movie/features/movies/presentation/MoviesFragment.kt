package com.konstantin.kustov.movie.features.movies.presentation

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

class MoviesFragment : BaseFragment() {

    companion object {

        private const val PARAM_SEARCH_LINE = "param_search_line"

        fun forMovies(searchLine: String?): MoviesFragment {
            val moviesFragment = MoviesFragment()
            val arguments = Bundle()
            arguments.putString(PARAM_SEARCH_LINE, searchLine)
            moviesFragment.arguments = arguments
            return moviesFragment
        }
    }

    @Inject
    lateinit var navigator: Navigator

    private lateinit var moviesViewModel: MoviesViewModel

    override fun layoutId() = R.layout.fragment_movies

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        setHasOptionsMenu(true)

        moviesViewModel = viewModel(viewModelFactory) {
            observe(movies, ::renderMovies)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
        loadMovies()
    }

    private fun initializeView() {
    }

    private fun loadMovies() {
        showProgress()
        val search: String? = arguments?.getString(PARAM_SEARCH_LINE)
        if (search != null) {
            moviesViewModel.loadMovies(search)
        } else {
            hideProgress()
        }
    }

    private fun renderMovies(movies: List<MovieModel>?) {
        hideProgress()
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> renderFailure(R.string.failure_network_connection)
            is Failure.ServerError -> renderFailure(R.string.failure_server_error)
            is MoviesFailure.MoviesNotAvailable -> renderFailure(R.string.failure_movies_unavailable)
            else -> renderFailure(R.string.failure_unknown_error)
        }
    }

    private fun renderFailure(@StringRes message: Int) {
        hideProgress()
        notifyWithAction(message, R.string.failure_action_refresh, ::loadMovies)
    }
}