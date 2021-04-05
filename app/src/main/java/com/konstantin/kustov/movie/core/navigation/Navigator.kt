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
        val searchLine: String? = preferencesStorage.getSearchName()
        val searchType: String? = preferencesStorage.getSearchType()
        if (searchLine == "" || searchLine == null || searchType == null) {
            showSearch(context)
        } else {
            showMovies(context, searchLine, searchType)
        }
    }

    fun showSearch(context: Context) =
        context.startActivity(SearchActivity.callingIntent(context))

    fun showMovies(context: Context, searchName: String, searchType: String) =
        context.startActivity(MoviesActivity.callingIntent(context, searchName, searchType))

    fun showMovieDetails(context: Context, imdbID: String) =
        context.startActivity(MovieDetailsActivity.callingIntent(context, imdbID))

    class Extras(val transitionSharedElement: View)
}


