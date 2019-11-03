package com.example.nijatahmadli.data.repository.mapper

import com.example.nijatahmadli.common.DataResult
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class DataResultMapperImplTest {

    private val sut = DataResultMapperImpl()

    @Mock
    private lateinit var throwable: Throwable

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun map_should_returnDataResultSuccess_when_thereIsNoError() {
        val testObserver = sut.map(Observable.just("")).test()

        testObserver.assertValue { it is DataResult.Success }
    }

    @Test
    fun map_should_returnDataResultFailure_when_thereIsError() {
        val testObserver = sut.map(Observable.error<Any>(throwable)).test()

        testObserver.assertValue { it is DataResult.Failure }
            .assertValue { (it as DataResult.Failure).throwable == throwable }
    }
}