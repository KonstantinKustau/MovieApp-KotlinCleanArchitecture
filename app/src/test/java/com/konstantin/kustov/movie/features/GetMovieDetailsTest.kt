package com.konstantin.kustov.movie.features

import com.konstantin.kustov.movie.UnitTest
import com.konstantin.kustov.movie.core.functional.Either
import com.konstantin.kustov.movie.features.details.domain.GetMovieDetails
import com.konstantin.kustov.movie.features.details.domain.MovieDetailsEntity
import com.konstantin.kustov.movie.features.details.domain.MovieDetailsRepository
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetMovieDetailsTest : UnitTest() {

    companion object {
        private const val IMBD_ID = "tt3489184"
    }

    private lateinit var getMovieDetails: GetMovieDetails

    @MockK
    private lateinit var movieDetailsRepository: MovieDetailsRepository

    @Before
    fun setUp() {
        getMovieDetails = GetMovieDetails(movieDetailsRepository)
        every { movieDetailsRepository.getMovieDetails(IMBD_ID) } returns Either.Right(
            MovieDetailsEntity.empty
        )
    }

    @Test
    fun `should get data from repository`() {
        runBlocking { getMovieDetails.run(GetMovieDetails.Params(IMBD_ID)) }
        verify(exactly = 1) { movieDetailsRepository.getMovieDetails(IMBD_ID) }
    }
}
