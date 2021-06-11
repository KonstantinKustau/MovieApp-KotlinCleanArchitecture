package com.konstantin.kustov.movie.core.extension

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

val Context.connectivityManager: ConnectivityManager
    get() =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager