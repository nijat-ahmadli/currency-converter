package com.example.nijatahmadli.domain.usecase

import com.example.nijatahmadli.common.DataResult
import com.example.nijatahmadli.domain.model.Currencies
import com.example.nijatahmadli.domain.repository.CurrenciesRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetCurrenciesUseCaseImpl @Inject constructor(
    private val repository: CurrenciesRepository
) : GetCurrenciesUseCase {


    override fun invoke(base: String): Observable<DataResult<Currencies>> {
        return repository.getCurrencies(base)
    }
}