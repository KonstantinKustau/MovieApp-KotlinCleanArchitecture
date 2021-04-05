package com.konstantin.kustov.movie.features.movies.presentation

import androidx.lifecycle.MutableLiveData
import com.konstantin.kustov.movie.core.platform.BaseViewModel
import com.konstantin.kustov.movie.features.movies.domain.GetMovies
import com.konstantin.kustov.movie.features.movies.domain.MovieEntity
import com.konstantin.kustov.movie.features.movies.presentation.recyclerview.MovieView
import javax.inject.Inject

class MoviesViewModel
@Inject constructor(
    private val getMovies: GetMovies
) : BaseViewModel() {

    var movies: MutableLiveData<List<MovieView>> = MutableLiveData()

    fun loadMovies(searchLine: String, searchType: String) {
        getMovies(GetMovies.Params(searchLine, searchType)) { it ->
            it.fold(
                { handleFailure(it) },
                { handleMovies(it) })
        }
    }

    private fun handleMovies(moviesList: List<MovieEntity>) {
        val moviesArrayList: ArrayList<MovieView> = arrayListOf()
        moviesList.map {
            moviesArrayList.add(
                MovieView(
                    it.title,
                    it.imdbId,
                    it.poster
                )
            )
        }
        movies.value = moviesArrayList
    }
}