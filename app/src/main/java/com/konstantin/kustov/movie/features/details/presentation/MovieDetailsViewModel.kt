package com.konstantin.kustov.movie.features.details.presentation

import androidx.lifecycle.MutableLiveData
import com.konstantin.kustov.movie.core.platform.BaseViewModel
import javax.inject.Inject

class MovieDetailsViewModel
@Inject constructor(
) : BaseViewModel() {

    var movieDetails: MutableLiveData<MovieDetailsModel> = MutableLiveData()

    fun loadMovieDetails(imdbID: String) {
    }

    private fun handleMovieDetails(deals: MovieDetailsModel) {
    }
}