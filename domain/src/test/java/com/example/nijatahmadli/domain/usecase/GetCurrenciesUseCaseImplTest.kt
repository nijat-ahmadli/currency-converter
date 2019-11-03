package com.example.nijatahmadli.domain.usecase

import com.example.nijatahmadli.common.DataResult
import com.example.nijatahmadli.domain.model.Currencies
import com.example.nijatahmadli.domain.repository.CurrenciesRepository
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetCurrenciesUseCaseImplTest {

    private companion object {
        const val BASE = "EUR"
    }

    @Mock
    private lateinit var currenciesRepository: CurrenciesRepository

    private lateinit var sut: GetCurrenciesUseCase

    @Mock
    private lateinit var dataResult: DataResult<Currencies>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sut = GetCurrenciesUseCaseImpl(currenciesRepository)
    }

    @Test
    fun invoke_should_returnCurrenciesDataResult() {
        whenever(currenciesRepository.getCurrencies(BASE)).thenReturn(Observable.just(dataResult))

        val testObserver = sut(BASE).test()

        testObserver.assertValue(dataResult)
    }
}