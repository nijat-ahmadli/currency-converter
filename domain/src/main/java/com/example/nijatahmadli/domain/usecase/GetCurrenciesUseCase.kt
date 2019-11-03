package com.example.nijatahmadli.domain.usecase

import com.example.nijatahmadli.common.DataResult
import com.example.nijatahmadli.domain.model.Currencies
import io.reactivex.Observable

interface GetCurrenciesUseCase {

    operator fun invoke(base: String): Observable<DataResult<Currencies>>
}