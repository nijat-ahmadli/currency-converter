package com.example.nijatahmadli.data.datasource.remote.mapper

import com.example.nijatahmadli.common.mapper.MapperWithNullableInput
import com.example.nijatahmadli.data.datasource.remote.model.NetworkCurrencies
import com.example.nijatahmadli.domain.model.Currencies.Companion.BASE_DEFAULT
import com.example.nijatahmadli.domain.model.Currencies.Companion.DATE_DEFAULT
import com.example.nijatahmadli.domain.model.Rate
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Before
import org.junit.Test

import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.math.BigDecimal

class NetworkToDomainCurrenciesMapperTest {

    private companion object {
        const val BASE = "EUR"
        const val DATE = "date"
    }

    @Mock
    private lateinit var ratesMapper: MapperWithNullableInput<Map<String, BigDecimal>, List<Rate>>

    private lateinit var sut: NetworkToDomainCurrenciesMapper

    @Mock
    private lateinit var networkCurrencies: NetworkCurrencies
    @Mock
    private lateinit var networkRates: Map<String, BigDecimal>
    @Mock
    private lateinit var rates: List<Rate>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sut = NetworkToDomainCurrenciesMapper(ratesMapper)
        mockNetworkCurrencies()
        whenever(ratesMapper.map(networkRates)).thenReturn(rates)
    }

    private fun mockNetworkCurrencies() {
        with(networkCurrencies) {
            whenever(base).thenReturn(BASE)
            whenever(date).thenReturn(DATE)
            whenever(rates).thenReturn(networkRates)
        }
    }

    @Test
    fun map_should_mapDefaultValues_when_providedValuesAreNull() {
        with(networkCurrencies) {
            whenever(base).thenReturn(null)
            whenever(date).thenReturn(null)
        }

        val result = sut.map(networkCurrencies)

        assertThat(result.base).isEqualTo(BASE_DEFAULT)
        assertThat(result.date).isEqualTo(DATE_DEFAULT)
        assertThat(result.rates).isEqualTo(rates)
    }

    @Test
    fun map_should_mapProvidedValues_when_providedValuesAreNotNull() {
        val result = sut.map(networkCurrencies)

        assertThat(result.base).isEqualTo(BASE)
        assertThat(result.date).isEqualTo(DATE)
        assertThat(result.rates).isEqualTo(rates)
    }
}