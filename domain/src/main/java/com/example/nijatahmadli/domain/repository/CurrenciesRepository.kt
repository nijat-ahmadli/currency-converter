package com.example.nijatahmadli.domain.repository

import com.example.nijatahmadli.common.DataResult
import com.example.nijatahmadli.domain.model.Currencies
import io.reactivex.Observable

interface CurrenciesRepository {

    fun getCurrencies(base: String): Observable<DataResult<Currencies>>
}