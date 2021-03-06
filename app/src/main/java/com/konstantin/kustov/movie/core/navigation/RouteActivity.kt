package com.konstantin.kustov.movie.core.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.konstantin.kustov.movie.AndroidApplication
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RouteActivity : AppCompatActivity() {

    @Inject
    internal lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigator.showFirstScreen(this)
    }
}