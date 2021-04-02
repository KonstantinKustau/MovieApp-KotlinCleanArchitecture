package com.konstantin.kustov.movie.core.extension

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

//TODO check all extensions
val Context.networkInfo: NetworkInfo?
    get() =
        (this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo