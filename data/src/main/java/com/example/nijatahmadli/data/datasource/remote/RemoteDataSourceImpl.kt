package com.example.nijatahmadli.data.datasource.remote

import com.example.nijatahmadli.common.DataResult
import com.example.nijatahmadli.data.datasource.remote.api.CurrenciesApi
import com.example.nijatahmadli.data.datasource.remote.model.NetworkCurrencies
import com.example.nijatahmadli.data.repository.mapper.DataResultMapper
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val currenciesApi: CurrenciesApi,
    private val dataResultMapper: DataResultMapper
) : RemoteDataSource {

    override fun getCurrencies(base: String): Observable<DataResult<NetworkCurrencies>> {
        return dataResultMapper.map(
            currenciesApi.getCurrencies(base)
        ).subscribeOn(Schedulers.io())
    }
}