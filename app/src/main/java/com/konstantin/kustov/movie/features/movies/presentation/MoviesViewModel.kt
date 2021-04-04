package com.konstantin.kustov.movie.features.movies.presentation

import androidx.lifecycle.MutableLiveData
import com.konstantin.kustov.movie.core.platform.BaseViewModel
import javax.inject.Inject

class MoviesViewModel
@Inject constructor(
) : BaseViewModel() {

    var movies: MutableLiveData<List<MovieModel>> = MutableLiveData()

    fun loadMovies(searchLine: String, searchType: String) {
    }

    private fun handleMovies(deals: MovieModel) {
    }
}