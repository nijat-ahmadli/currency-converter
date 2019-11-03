package com.example.nijatahmadli.data.repository

import com.example.nijatahmadli.common.DataResult
import com.example.nijatahmadli.common.mapper.Mapper
import com.example.nijatahmadli.data.datasource.remote.RemoteDataSource
import com.example.nijatahmadli.data.datasource.remote.model.NetworkCurrencies
import com.example.nijatahmadli.domain.model.Currencies
import com.example.nijatahmadli.domain.repository.CurrenciesRepository
import io.reactivex.Observable
import javax.inject.Inject

class CurrenciesRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val networkToDomainCurrenciesDataResultMapper: Mapper<DataResult<NetworkCurrencies>, DataResult<Currencies>>
) : CurrenciesRepository {

    override fun getCurrencies(base: String): Observable<DataResult<Currencies>> {
        return remoteDataSource.getCurrencies(base)
            .map(networkToDomainCurrenciesDataResultMapper::map)
    }
}