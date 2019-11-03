package com.example.nijatahmadli.converter.presentation.converter.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.nijatahmadli.common.DataResult
import com.example.nijatahmadli.converter.presentation.converter.viewModel.ConverterViewModelImpl.Companion.REQUEST_PERIOD
import com.example.nijatahmadli.domain.model.Currencies
import com.example.nijatahmadli.domain.model.Rate
import com.example.nijatahmadli.domain.usecase.GetCurrenciesUseCase
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockito_kotlin.whenever
import com.example.nijatahmadli.converter.presentation.utils.LiveDataTestObserver
import com.example.nijatahmadli.converter.presentation.utils.test
import io.reactivex.Observable
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.util.concurrent.TimeUnit

class ConverterViewModelImplTest {

    private companion object {
        const val ERROR_MESSAGE = "error_message"
    }

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var baserRate: Rate
    @Mock
    private lateinit var getCurrenciesUseCase: GetCurrenciesUseCase

    private lateinit var sut: ConverterViewModelImpl

    @Mock
    private lateinit var dataResultSuccess: DataResult.Success<Currencies>
    @Mock
    private lateinit var dataResultFailure: DataResult.Failure
    @Mock
    private lateinit var currencies: Currencies
    @Mock
    private lateinit var rates: List<Rate>
    @Mock
    private lateinit var throwable: Throwable

    private lateinit var showLoadingTestObserver: LiveDataTestObserver<Boolean>
    private lateinit var showErrorTestObserver: LiveDataTestObserver<String>

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        sut =
            ConverterViewModelImpl(
                baserRate,
                getCurrenciesUseCase
            )
        showLoadingTestObserver = sut.showLoading.test()
        showErrorTestObserver = sut.showError.test()
    }

    @After
    fun tearDown() {
        RxJavaPlugins.reset()
    }

    @Test
    fun getCurrencies_should_emitRate_when_getCurrenciesReturnsSuccess() {
        val testScheduler = TestScheduler()
        RxJavaPlugins.setIoSchedulerHandler { testScheduler }

        whenever(getCurrenciesUseCase(baserRate.code)).thenReturn(Observable.just(dataResultSuccess))
        whenever(dataResultSuccess.value).thenReturn(currencies)
        whenever(currencies.rates).thenReturn(rates)

        sut.rates.observeForever {}
        sut.getCurrencies()

        testScheduler.advanceTimeTo(REQUEST_PERIOD, TimeUnit.SECONDS)
        assertThat(sut.rates.value).isEqualTo(currencies.rates)
        showLoadingTestObserver.assertValues(true, false)
    }

    @Test
    fun getCurrencies_should_emitError_when_getCurrenciesReturnsFailure() {
        val testScheduler = TestScheduler()
        RxJavaPlugins.setIoSchedulerHandler { testScheduler }

        whenever(getCurrenciesUseCase(baserRate.code)).thenReturn(Observable.just(dataResultFailure))
        whenever(dataResultFailure.throwable).thenReturn(throwable)
        whenever(throwable.message).thenReturn(ERROR_MESSAGE)

        sut.rates.observeForever {}
        sut.getCurrencies()

        testScheduler.advanceTimeTo(REQUEST_PERIOD, TimeUnit.SECONDS)
        showErrorTestObserver.assertValues(ERROR_MESSAGE)
        showLoadingTestObserver.assertValues(true, false)
    }
}