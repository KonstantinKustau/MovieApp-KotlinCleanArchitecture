package com.konstantin.kustov.movie.features.movies.presentation

import android.content.Context
import android.content.Intent
import com.konstantin.kustov.movie.core.platform.BaseActivity

class MoviesActivity : BaseActivity() {

    companion object {
        private const val INTENT_EXTRA_PARAM_SEARCH_LINE =
            "com.thebestdiscountandroid.INTENT_EXTRA_PARAM_SEARCH_LINE"
        private const val INTENT_EXTRA_PARAM_SEARCH_TYPE =
            "com.thebestdiscountandroid.INTENT_EXTRA_PARAM_SEARCH_TYPE"

        fun callingIntent(context: Context, searchLine: String, searchType: String): Intent {
            val intent = Intent(context, MoviesActivity::class.java)
            intent.putExtra(INTENT_EXTRA_PARAM_SEARCH_LINE, searchLine)
            intent.putExtra(INTENT_EXTRA_PARAM_SEARCH_TYPE, searchType)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK;
            return intent
        }
    }

    override fun fragment() = MoviesFragment.forMovies(
        intent.getStringExtra(INTENT_EXTRA_PARAM_SEARCH_LINE),
        intent.getStringExtra(INTENT_EXTRA_PARAM_SEARCH_TYPE)
    )
}