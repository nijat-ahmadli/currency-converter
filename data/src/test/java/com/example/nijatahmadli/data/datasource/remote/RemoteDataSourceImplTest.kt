package com.example.nijatahmadli.data.datasource.remote

import com.example.nijatahmadli.common.DataResult
import com.example.nijatahmadli.data.RxSchedulersOverrideRule
import com.example.nijatahmadli.data.datasource.remote.api.CurrenciesApi
import com.example.nijatahmadli.data.datasource.remote.model.NetworkCurrencies
import com.example.nijatahmadli.data.repository.mapper.DataResultMapper
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class RemoteDataSourceImplTest {

    @Rule
    @JvmField
    val overrideSchedulersRule = RxSchedulersOverrideRule()

    private companion object {
        const val BASE = "EUR"
    }

    @Mock
    private lateinit var currenciesApi: CurrenciesApi

    @Mock
    private lateinit var dataResultMapper: DataResultMapper

    private lateinit var sut: RemoteDataSourceImpl

    @Mock
    private lateinit var observable: Observable<NetworkCurrencies>
    @Mock
    private lateinit var dataResult: DataResult<NetworkCurrencies>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sut = RemoteDataSourceImpl(currenciesApi, dataResultMapper)
    }

    @Test
    fun getCurrencies_should_returnDataResultNetworkCurrencies() {
        whenever(currenciesApi.getCurrencies(BASE)).thenReturn(observable)
        whenever(dataResultMapper.map(observable)).thenReturn(Observable.just(dataResult))

        val testObserver = sut.getCurrencies(BASE).test()

        testObserver.assertValue(dataResult)
    }
}