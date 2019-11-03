package com.example.nijatahmadli.data.datasource.remote.api

import com.example.nijatahmadli.data.datasource.remote.model.NetworkCurrencies
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrenciesApi {

    @GET("latest")
    fun getCurrencies(@Query("base") base: String): Observable<NetworkCurrencies>
}