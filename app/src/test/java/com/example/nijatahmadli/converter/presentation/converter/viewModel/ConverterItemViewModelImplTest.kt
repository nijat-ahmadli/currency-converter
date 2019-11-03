package com.example.nijatahmadli.converter.presentation.converter.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.nijatahmadli.domain.model.Rate
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.math.BigDecimal

class ConverterItemViewModelImplTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private companion object {
        const val RATE_CODE = "EUR"
        val RATE_VALUE = BigDecimal(1.0)
    }

    private val sut = ConverterItemViewModelImpl()

    @Mock
    private lateinit var rate: Rate

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun setModel_should_currencyCodeAndValue() {
        whenever(rate.code).thenReturn(RATE_CODE)
        whenever(rate.value).thenReturn(RATE_VALUE)

        sut.setModel(rate)

        assertThat(sut.currencyCode.value).isEqualTo(RATE_CODE)
        assertThat(sut.currencyValue.value).isEqualTo(RATE_VALUE)
    }
}