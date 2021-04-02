package com.konstantin.kustov.movie.features.search.presentation

import android.content.Context
import android.content.Intent
import com.konstantin.kustov.movie.core.platform.BaseActivity

class SearchActivity : BaseActivity() {

    companion object {

        fun callingIntent(context: Context): Intent {
            val intent = Intent(context, SearchActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK;
            return intent
        }
    }

    override fun fragment() = SearchFragment()

}