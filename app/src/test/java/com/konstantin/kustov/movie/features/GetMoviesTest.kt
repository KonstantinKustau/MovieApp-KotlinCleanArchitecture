package com.konstantin.kustov.movie.features

import com.konstantin.kustov.movie.UnitTest
import com.konstantin.kustov.movie.core.functional.Either
import com.konstantin.kustov.movie.features.movies.domain.GetMovies
import com.konstantin.kustov.movie.features.movies.domain.MovieEntity
import com.konstantin.kustov.movie.features.movies.domain.MoviesRepository
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetMoviesTest : UnitTest() {

    companion object {
        private const val SEARCH_LINE = "Constantine"
        private const val SEARCH_TYPE = "movie"
    }

    private lateinit var getMovies: GetMovies

    @MockK
    private lateinit var moviesRepository: MoviesRepository

    @Before
    fun setUp() {
        getMovies = GetMovies(moviesRepository)
        every { moviesRepository.getMovies(SEARCH_LINE, SEARCH_TYPE) } returns Either.Right(
            listOf(
                MovieEntity.empty
            )
        )
    }

    @Test
    fun `should get data from repository`() {
        runBlocking { getMovies.run(GetMovies.Params(SEARCH_LINE, SEARCH_TYPE)) }
        verify(exactly = 1) { moviesRepository.getMovies(SEARCH_LINE, SEARCH_TYPE) }
    }
}
