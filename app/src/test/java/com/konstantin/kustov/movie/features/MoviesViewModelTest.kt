package com.konstantin.kustov.movie.features

import android.graphics.Movie
import com.konstantin.kustov.movie.AndroidTest
import com.konstantin.kustov.movie.core.functional.Either
import com.konstantin.kustov.movie.features.movies.domain.GetMovies
import com.konstantin.kustov.movie.features.movies.domain.MovieEntity
import com.konstantin.kustov.movie.features.movies.presentation.MoviesViewModel
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldEqualTo
import org.junit.Before
import org.junit.Test

class MoviesViewModelTest : AndroidTest() {

    private lateinit var moviesViewModel: MoviesViewModel

    @MockK
    private lateinit var getMovies: GetMovies

    @Before
    fun setUp() {
        moviesViewModel = MoviesViewModel(getMovies)
    }

    @Test
    fun `loading movies should update live data`() {
        val moviesList = listOf(
            MovieEntity(
                "Constantine: City of Demons",
                "tt6404896",
                "https://m.media-amazon.com/images/M/MV5BNmUxYzEwMDktNTc1ZC00NDMzLWE2OGYtNmNlYWE3OWUzY2FlXkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_SX300.jpg"
            ),
            MovieEntity(
                "Constantine",
                "tt0360486",
                "https://m.media-amazon.com/images/M/MV5BODRiNmFhY2EtMGY2OC00YjI2LWIyYjQtYzFiM2ZhNjdhYzE4XkEyXkFqcGdeQXVyNDY5MTUyNjU@._V1_SX300.jpg"
            ),
            MovieEntity(
                "Constantine: The Legend Continues",
                "tt8906438",
                "https://m.media-amazon.com/images/M/MV5BNWM1Zjk1MjctNjE1Yi00NGI1LWJiYmEtZjg1Y2E2MTc5YjRkXkEyXkFqcGdeQXVyODY0NjQ4ODY@._V1_SX300.jpg"
            )
        )
        coEvery { getMovies.run(any()) } returns Either.Right(moviesList)

        moviesViewModel.movies.observeForever {
            it!!.size shouldEqualTo 3
            it[0].title shouldEqualTo "Constantine: City of Demons"
            it[0].imdbId shouldEqualTo "tt6404896"
            it[0].poster shouldEqualTo "https://m.media-amazon.com/images/M/MV5BNmUxYzEwMDktNTc1ZC00NDMzLWE2OGYtNmNlYWE3OWUzY2FlXkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_SX300.jpg"
            it[1].title shouldEqualTo "Constantine"
            it[1].imdbId shouldEqualTo "tt0360486"
            it[1].poster shouldEqualTo "https://m.media-amazon.com/images/M/MV5BODRiNmFhY2EtMGY2OC00YjI2LWIyYjQtYzFiM2ZhNjdhYzE4XkEyXkFqcGdeQXVyNDY5MTUyNjU@._V1_SX300.jpg"
            it[2].title shouldEqualTo "Constantine: The Legend Continues"
            it[2].imdbId shouldEqualTo "tt8906438"
            it[2].poster shouldEqualTo "https://m.media-amazon.com/images/M/MV5BNWM1Zjk1MjctNjE1Yi00NGI1LWJiYmEtZjg1Y2E2MTc5YjRkXkEyXkFqcGdeQXVyODY0NjQ4ODY@._V1_SX300.jpg"
        }

        runBlocking { moviesViewModel.loadMovies("Constantine", "") }
    }
}