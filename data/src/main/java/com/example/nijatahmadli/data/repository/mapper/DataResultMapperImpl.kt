package com.example.nijatahmadli.data.repository.mapper

import com.example.nijatahmadli.common.DataResult
import io.reactivex.Observable
import javax.inject.Inject

class DataResultMapperImpl @Inject constructor() : DataResultMapper {

    override fun <T> map(input: Observable<T>): Observable<DataResult<T>> {
        return input
            .map { DataResult.Success(it) as DataResult<T> }
            .onErrorResumeNext { throwable: Throwable ->
                Observable.just(DataResult.Failure(throwable))
            }
    }
}