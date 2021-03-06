package com.konstantin.kustov.movie.features.movies.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.annotation.StringRes
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.konstantin.kustov.movie.R
import com.konstantin.kustov.movie.core.exception.Failure
import com.konstantin.kustov.movie.core.extension.*
import com.konstantin.kustov.movie.core.navigation.Navigator
import com.konstantin.kustov.movie.core.platform.BaseFragment
import com.konstantin.kustov.movie.features.movies.presentation.recyclerview.MovieAdapter
import com.konstantin.kustov.movie.features.movies.presentation.recyclerview.MovieView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.android.synthetic.main.layout_empty_internet.*
import kotlinx.android.synthetic.main.layout_empty_search.*
import javax.inject.Inject

@AndroidEntryPoint
class MoviesFragment : BaseFragment() {

    companion object {

        private const val PARAM_SEARCH_LINE = "param_search_line"
        private const val PARAM_SEARCH_TYPE = "param_search_type"

        fun forMovies(searchLine: String?, searchType: String?): MoviesFragment {
            val moviesFragment = MoviesFragment()
            val arguments = Bundle()
            arguments.putString(PARAM_SEARCH_LINE, searchLine)
            arguments.putString(PARAM_SEARCH_TYPE, searchType)
            moviesFragment.arguments = arguments
            return moviesFragment
        }
    }

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var movieAdapter: MovieAdapter

    private val moviesViewModel: MoviesViewModel by viewModels()

    override fun layoutId() = R.layout.fragment_movies

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        with(moviesViewModel) {
            observe(movies, ::renderMovies)
            failure(failure, ::handleFailure)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_movies, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.actionSearch) {
            navigator.showSearch(appContext)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
        loadMovies()
    }

    private fun initializeView() {
        movieRecyclerView.layoutManager =
            StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        movieRecyclerView.adapter = movieAdapter
        movieAdapter.clickListener = {
            navigator.showMovieDetails(appContext, it.imdbId)
        }
    }

    private fun loadMovies() {
        val search: String? = arguments?.getString(PARAM_SEARCH_LINE)
        val type: String? = arguments?.getString(PARAM_SEARCH_TYPE)
        if (search != null && type != null) {
            moviesViewModel.loadMovies(search, type)
        }
        movieRecyclerView.visible()
        emptyInternetView.invisible()
        emptySearchView.invisible()
    }

    private fun renderMovies(movies: List<MovieView>?) {
        if (movies != null) {
            if (movies.isEmpty()) {
                handleFailure(MoviesFailure.MoviesIsEmpty())
            } else {
                movieAdapter.collection = movies as ArrayList<MovieView>
            }
        } else {
            handleFailure(MoviesFailure.MoviesNotAvailable())
        }
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> {
                renderFailureWithAction(R.string.failure_network_connection)
                emptyInternetView.visible()
            }
            is Failure.ServerError -> {
                renderFailureWithAction(R.string.failure_server_error)
                emptyInternetView.visible()
            }
            is MoviesFailure.MoviesNotAvailable -> {
                renderFailure(R.string.failure_movies_unavailable)
                emptySearchView.visible()
            }
            is MoviesFailure.MoviesIsEmpty -> {
                renderFailure(R.string.failure_movies_is_empty)
                emptySearchView.visible()
            }
            else -> {
                renderFailure(R.string.failure_unknown_error)
                emptySearchView.visible()
            }
        }
    }

    private fun renderFailure(@StringRes message: Int) {
        notify(message)
        movieRecyclerView.invisible()
    }

    private fun renderFailureWithAction(@StringRes message: Int) {
        notifyWithAction(message, R.string.failure_action_refresh, ::loadMovies)
        movieRecyclerView.invisible()
    }
}