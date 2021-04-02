package com.konstantin.kustov.movie

import android.app.Application
import com.konstantin.kustov.movie.core.di.ApplicationComponent
import com.konstantin.kustov.movie.core.di.ApplicationModule
import com.konstantin.kustov.movie.core.di.DaggerApplicationComponent

class AndroidApplication : Application() {

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        this.injectMembers()
    }

    private fun injectMembers() = appComponent.inject(this)

}