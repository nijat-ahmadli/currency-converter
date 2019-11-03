package com.example.nijatahmadli.data.repository.mapper

import com.example.nijatahmadli.common.DataResult
import com.example.nijatahmadli.common.mapper.Mapper
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Before
import org.junit.Test

import org.mockito.Mock
import org.mockito.MockitoAnnotations

class NetworkToDomainDataResultMapperTest {

    @Mock
    private lateinit var objectMapper: Mapper<Any, Any>

    private lateinit var sut: NetworkToDomainDataResultMapper<Any, Any>

    @Mock
    private lateinit var input: Any
    @Mock
    private lateinit var output: Any
    @Mock
    private lateinit var dataResultSuccess: DataResult.Success<Any>
    @Mock
    private lateinit var dataResultFailure: DataResult.Failure

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sut = NetworkToDomainDataResultMapper(objectMapper)
    }

    @Test
    fun map_should_returnDataResultSuccessWithMappedObject_when_dataResultIsSuccess() {
        whenever(dataResultSuccess.value).thenReturn(input)
        whenever(objectMapper.map(input)).thenReturn(output)

        val result = sut.map(dataResultSuccess)

        assertThat(result).isInstanceOf(DataResult.Success::class.java)
        assertThat((result as DataResult.Success).value).isEqualTo(output)
    }

    @Test
    fun map_should_returnDataResultFailure_when_dataResultIsFailure() {
        val result = sut.map(dataResultFailure)

        assertThat(result).isInstanceOf(DataResult.Failure::class.java)
    }
}