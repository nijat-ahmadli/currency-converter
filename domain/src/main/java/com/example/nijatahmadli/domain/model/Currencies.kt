package com.example.nijatahmadli.domain.model

data class Currencies(
    val base: String,
    val date: String,
    val rates: List<Rate>
) {
    companion object {
        const val BASE_DEFAULT = ""
        const val DATE_DEFAULT = ""
    }
}