package com.konstantin.kustov.movie.core.navigation

import android.content.Context
import android.view.View
import com.konstantin.kustov.movie.core.storage.PreferencesStorage
import com.konstantin.kustov.movie.features.details.presentation.MovieDetailsActivity
import com.konstantin.kustov.movie.features.movies.presentation.MoviesActivity
import com.konstantin.kustov.movie.features.search.presentation.SearchActivity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Navigator
@Inject constructor() {

    @Inject
    lateinit var preferencesStorage: PreferencesStorage

    fun showFirstScreen(context: Context) {
        when (val searchLine: String? = preferencesStorage.getSearchName()) {
            "", null -> showSearch(context)
            else -> showMovies(context, searchLine)
        }
    }

    fun showSearch(context: Context) =
        context.startActivity(SearchActivity.callingIntent(context))

    fun showMovies(context: Context, searchName: String) =
        context.startActivity(MoviesActivity.callingIntent(context, searchName))

    fun showMovieDetails(context: Context, imdbID: String) =
        context.startActivity(MovieDetailsActivity.callingIntent(context, imdbID))

    class Extras(val transitionSharedElement: View)
}


