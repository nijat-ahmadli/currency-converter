package com.example.nijatahmadli.data.repository.mapper

import com.example.nijatahmadli.common.DataResult
import com.example.nijatahmadli.common.mapper.Mapper
import javax.inject.Inject

class NetworkToDomainDataResultMapper<I, O> @Inject constructor(
    private val objectMapper: Mapper<I, O>
) : Mapper<DataResult<I>, DataResult<O>> {

    override fun map(input: DataResult<I>): DataResult<O> {
        return when (input) {
            is DataResult.Success -> DataResult.Success(objectMapper.map(input.value))
            is DataResult.Failure -> input
        }
    }
}