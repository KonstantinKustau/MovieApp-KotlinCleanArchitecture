package com.konstantin.kustov.movie.features.details.presentation

import androidx.lifecycle.MutableLiveData
import com.konstantin.kustov.movie.core.platform.BaseViewModel
import com.konstantin.kustov.movie.features.details.domain.GetMovieDetails
import com.konstantin.kustov.movie.features.details.domain.MovieDetailsEntity
import javax.inject.Inject

class MovieDetailsViewModel
@Inject constructor(
    private val getMovieDetails: GetMovieDetails
) : BaseViewModel() {

    var movieDetails: MutableLiveData<MovieDetailsModel> = MutableLiveData()

    fun loadMovieDetails(imdbID: String) {
        getMovieDetails(GetMovieDetails.Params(imdbID)) { it ->
            it.fold(
                { handleFailure(it) },
                { handleMovieDetails(it) })
        }
    }

    private fun handleMovieDetails(details: MovieDetailsEntity) {
        movieDetails.value = MovieDetailsModel(
            details.title,
            details.plot,
            details.genre,
            details.actors,
            details.year,
            details.type,
            details.runtime,
            details.poster
        )
    }
}