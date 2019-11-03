package com.example.nijatahmadli.data.datasource.remote.mapper

import com.example.nijatahmadli.common.mapper.Mapper
import com.example.nijatahmadli.common.mapper.MapperWithNullableInput
import com.example.nijatahmadli.data.datasource.remote.model.NetworkCurrencies
import com.example.nijatahmadli.domain.model.Currencies
import com.example.nijatahmadli.domain.model.Currencies.Companion.BASE_DEFAULT
import com.example.nijatahmadli.domain.model.Currencies.Companion.DATE_DEFAULT
import com.example.nijatahmadli.domain.model.Rate
import java.math.BigDecimal
import javax.inject.Inject

class NetworkToDomainCurrenciesMapper @Inject constructor(
    private val ratesMapper: MapperWithNullableInput<Map<String, BigDecimal>, List<Rate>>
) : Mapper<NetworkCurrencies, Currencies> {

    override fun map(input: NetworkCurrencies): Currencies {
        return with(input) {
            Currencies(
                base ?: BASE_DEFAULT,
                date ?: DATE_DEFAULT,
                ratesMapper.map(rates)
            )
        }
    }
}