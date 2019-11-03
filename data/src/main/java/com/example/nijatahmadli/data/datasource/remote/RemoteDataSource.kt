package com.example.nijatahmadli.data.datasource.remote

import com.example.nijatahmadli.common.DataResult
import com.example.nijatahmadli.data.datasource.remote.model.NetworkCurrencies
import io.reactivex.Observable

interface RemoteDataSource {

    fun getCurrencies(base: String): Observable<DataResult<NetworkCurrencies>>
}