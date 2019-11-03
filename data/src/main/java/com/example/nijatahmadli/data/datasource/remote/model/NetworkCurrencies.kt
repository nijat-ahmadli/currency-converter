package com.example.nijatahmadli.data.datasource.remote.model

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class NetworkCurrencies(
    @SerializedName("base") val base: String?,
    @SerializedName("date") val date: String?,
    @SerializedName("rates") val rates: Map<String, BigDecimal>?
)