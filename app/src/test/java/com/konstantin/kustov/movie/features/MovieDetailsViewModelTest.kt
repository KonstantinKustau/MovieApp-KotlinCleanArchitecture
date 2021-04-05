package com.konstantin.kustov.movie.features

import com.konstantin.kustov.movie.AndroidTest
import com.konstantin.kustov.movie.core.functional.Either
import com.konstantin.kustov.movie.features.details.domain.GetMovieDetails
import com.konstantin.kustov.movie.features.details.domain.MovieDetailsEntity
import com.konstantin.kustov.movie.features.details.presentation.MovieDetailsViewModel
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldEqualTo
import org.junit.Before
import org.junit.Test

class MovieDetailsViewModelTest : AndroidTest() {

    private lateinit var movieDetailsViewModel: MovieDetailsViewModel

    @MockK
    private lateinit var getMovieDetails: GetMovieDetails

    @Before
    fun setUp() {
        movieDetailsViewModel = MovieDetailsViewModel(getMovieDetails)
    }

    @Test
    fun `loading movie details should update live data`() {
        val movieDetailsEntity = MovieDetailsEntity(
            "Brad Pitt",
            "Horror",
            "Description",
            "https://m.media-amazon.com/images/M/MV5BMTQ2MzQzMjA2NF5BMl5BanBnXkFtZTgwODg1MTI4MjE@._V1_SX300.jpg",
            "120 min",
            "Constantine",
            "series",
            "2014–2015",
            "tt3489184"
        )
        coEvery { getMovieDetails.run(any()) } returns Either.Right(movieDetailsEntity)

        movieDetailsViewModel.movieDetails.observeForever {
            with(it!!) {
                actors shouldEqualTo "Brad Pitt"
                genre shouldEqualTo "Horror"
                plot shouldEqualTo "Description"
                poster shouldEqualTo "https://m.media-amazon.com/images/M/MV5BMTQ2MzQzMjA2NF5BMl5BanBnXkFtZTgwODg1MTI4MjE@._V1_SX300.jpg"
                runtime shouldEqualTo "120 min"
                title shouldEqualTo "Constantine"
                type shouldEqualTo "series"
                year shouldEqualTo "2014–2015"
            }
        }

        runBlocking { movieDetailsViewModel.loadMovieDetails("tt3489184") }
    }
}