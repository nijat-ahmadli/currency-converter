package com.example.nijatahmadli.data.datasource.remote.mapper

import com.example.nijatahmadli.common.mapper.MapperWithNullableInput
import com.example.nijatahmadli.domain.model.Rate
import java.math.BigDecimal
import javax.inject.Inject

class NetworkToDomainRatesMapper @Inject constructor() :
    MapperWithNullableInput<Map<String, BigDecimal>, List<Rate>> {

    override fun map(input: Map<String, BigDecimal>?): List<Rate> {
        return input?.entries?.map { Rate(it.key, it.value) } ?: emptyList()
    }
}