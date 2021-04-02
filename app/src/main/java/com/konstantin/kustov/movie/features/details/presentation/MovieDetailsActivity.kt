package com.konstantin.kustov.movie.features.details.presentation

import android.content.Context
import android.content.Intent
import com.konstantin.kustov.movie.core.platform.BaseActivity

class MovieDetailsActivity : BaseActivity() {

    companion object {
        private const val INTENT_EXTRA_PARAM_IMDB_ID =
                "com.thebestdiscountandroid.INTENT_EXTRA_PARAM_IMDB_ID"

        fun callingIntent(context: Context, imdbID: String): Intent {
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra(INTENT_EXTRA_PARAM_IMDB_ID, imdbID)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK;
            return intent
        }
    }

    override fun fragment() = MovieDetailsFragment.forMovieDetails(
            intent.getStringExtra(INTENT_EXTRA_PARAM_IMDB_ID)
    )
}