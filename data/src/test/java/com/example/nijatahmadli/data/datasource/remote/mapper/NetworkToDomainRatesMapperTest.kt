package com.example.nijatahmadli.data.datasource.remote.mapper

import com.example.nijatahmadli.domain.model.Rate
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import java.math.BigDecimal

class NetworkToDomainRatesMapperTest {

    private val sut = NetworkToDomainRatesMapper()

    private val map = mapOf("EUR" to BigDecimal(1), "GBP" to BigDecimal(2))

    @Test
    fun map_should_mapEmptyList_when_providedMapIsNull() {
        val result = sut.map(null)

        assertThat(result).isEmpty()
    }

    @Test
    fun map_should_mapValues_when_providedMapIsNotNull() {
        val result = sut.map(map)

        assertThat(result[0]).isInstanceOf(Rate::class.java)
        assertThat(result[0].code).isEqualTo("EUR")
        assertThat(result[0].value).isEqualTo(BigDecimal(1))
        assertThat(result[1].code).isEqualTo("GBP")
        assertThat(result[1].value).isEqualTo(BigDecimal(2))
    }
}