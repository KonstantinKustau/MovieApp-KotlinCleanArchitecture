package com.konstantin.kustov.movie.core.storage

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesStorage
@Inject constructor(private val context: Context) {

    private val key = "preferences_storage"

    private val searchName = "name"
    private val searchType = "type"

    private fun getSharedPreferences(): SharedPreferences {
        return context.getSharedPreferences(key, MODE_PRIVATE)
    }

    private fun getEditor(): SharedPreferences.Editor {
        return getSharedPreferences().edit()
    }

    fun setSearchName(name: String): Boolean {
        return getEditor().putString(searchName, name).commit()
    }

    fun getSearchName(): String? {
        return getSharedPreferences().getString(searchName, "")
    }

    fun setSearchType(type: String): Boolean {
        return getEditor().putString(searchType, type).commit()
    }

    fun getSearchType(): String? {
        return getSharedPreferences().getString(searchType, "")
    }
}