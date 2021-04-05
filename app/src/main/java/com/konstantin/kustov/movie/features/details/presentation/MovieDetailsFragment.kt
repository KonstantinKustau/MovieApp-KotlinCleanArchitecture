package com.konstantin.kustov.movie.features.details.presentation

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.konstantin.kustov.movie.R
import com.konstantin.kustov.movie.core.exception.Failure
import com.konstantin.kustov.movie.core.extension.*
import com.konstantin.kustov.movie.core.navigation.Navigator
import com.konstantin.kustov.movie.core.platform.BaseFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_empty_internet.*
import kotlinx.android.synthetic.main.layout_movie_details.*
import javax.inject.Inject

class MovieDetailsFragment : BaseFragment() {

    companion object {

        private const val PARAM_IMDB_ID = "param_imdb_id"

        fun forMovieDetails(imdbId: String?): MovieDetailsFragment {
            val movieDetailsFragment = MovieDetailsFragment()
            val arguments = Bundle()
            arguments.putString(PARAM_IMDB_ID, imdbId)
            movieDetailsFragment.arguments = arguments
            return movieDetailsFragment
        }
    }

    @Inject
    lateinit var navigator: Navigator

    private lateinit var movieDetailsViewModel: MovieDetailsViewModel

    override fun layoutId() = R.layout.fragment_movie_details

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        setHasOptionsMenu(true)

        movieDetailsViewModel = viewModel(viewModelFactory) {
            observe(movieDetails, ::renderMovieDetails)
            failure(failure, ::handleFailure)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            navigator.showFirstScreen(appContext)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
        loadMovieDetails()
    }

    override fun onBackPressed() {
        navigator.showFirstScreen(appContext)
    }

    private fun initializeView() {
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun loadMovieDetails() {
        val imdbId: String? = arguments?.getString(PARAM_IMDB_ID)
        if (imdbId != null) {
            movieDetailsViewModel.loadMovieDetails(imdbId)
        }
        container.visible()
        emptyInternetView.invisible()
    }

    private fun renderMovieDetails(movieDetails: MovieDetailsModel?) {
        if (movieDetails != null) {
            Picasso.get().load(movieDetails.poster).into(movieImage)
            movieName.text = movieDetails.title
            moviePlot.text = movieDetails.plot
            movieGenre.text = movieDetails.genre
            movieActors.text = movieDetails.actors
            movieYear.text = movieDetails.year
            movieType.text = movieDetails.type
            movieRuntime.text = movieDetails.runtime
        } else {
            handleFailure(MovieDetailsFailure.MovieDetailsNotAvailable())
        }
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> renderFailureWithAction(R.string.failure_network_connection)
            is Failure.ServerError -> renderFailureWithAction(R.string.failure_server_error)
            is MovieDetailsFailure.MovieDetailsNotAvailable -> renderFailure(R.string.failure_movie_details_unavailable)
            else -> renderFailure(R.string.failure_unknown_error)
        }
    }

    private fun renderFailure(@StringRes message: Int) {
        notify(message)
        container.invisible()
        emptyInternetView.visible()
    }

    private fun renderFailureWithAction(@StringRes message: Int) {
        notifyWithAction(message, R.string.failure_action_refresh, ::loadMovieDetails)
        container.invisible()
        emptyInternetView.visible()
    }
}