package com.example.nijatahmadli.data.repository

import com.example.nijatahmadli.common.DataResult
import com.example.nijatahmadli.common.mapper.Mapper
import com.example.nijatahmadli.data.datasource.remote.RemoteDataSource
import com.example.nijatahmadli.data.datasource.remote.model.NetworkCurrencies
import com.example.nijatahmadli.domain.model.Currencies
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class CurrenciesRepositoryImplTest {

    private companion object {
        const val BASE = "EUR"
    }

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource
    @Mock
    private lateinit var networkToDomainCurrenciesDataResultMapper: Mapper<DataResult<NetworkCurrencies>, DataResult<Currencies>>

    private lateinit var sut: CurrenciesRepositoryImpl

    @Mock
    private lateinit var dataResultNetworkCurrencies: DataResult<NetworkCurrencies>
    @Mock
    private lateinit var dataResultCurrencies: DataResult<Currencies>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sut = CurrenciesRepositoryImpl(remoteDataSource, networkToDomainCurrenciesDataResultMapper)
    }

    @Test
    fun getCurrencies_should_returnCurrenciesDataResult() {
        whenever(remoteDataSource.getCurrencies(BASE))
            .thenReturn(Observable.just(dataResultNetworkCurrencies))
        whenever(networkToDomainCurrenciesDataResultMapper.map(dataResultNetworkCurrencies))
            .thenReturn(dataResultCurrencies)

        val testObserver = sut.getCurrencies(BASE).test()

        testObserver.assertValue(dataResultCurrencies)
    }
}