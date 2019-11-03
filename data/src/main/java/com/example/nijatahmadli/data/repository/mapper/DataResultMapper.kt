package com.example.nijatahmadli.data.repository.mapper

import com.example.nijatahmadli.common.DataResult
import io.reactivex.Observable

interface DataResultMapper {

    fun <T> map(input: Observable<T>): Observable<DataResult<T>>
}