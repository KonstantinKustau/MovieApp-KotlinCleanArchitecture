package com.konstantin.kustov.movie.core.platform

import androidx.lifecycle.MutableLiveData
import com.konstantin.kustov.movie.AndroidTest
import com.konstantin.kustov.movie.core.exception.Failure
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.Test

class BaseViewModelTest : AndroidTest() {

    private class MyViewModel : BaseViewModel() {
        fun handleError(failure: Failure) = handleFailure(failure)
    }

    @Test fun `should handle failure by updating live data`() {
        val viewModel = MyViewModel()
        viewModel.handleError(Failure.NetworkConnection)
        val failure = viewModel.failure
        val error = viewModel.failure.value
        failure shouldBeInstanceOf MutableLiveData::class.java
        error shouldBeInstanceOf Failure.NetworkConnection::class.java
    }
}