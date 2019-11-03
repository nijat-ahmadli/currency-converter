package com.example.nijatahmadli.converter.presentation.converter.viewModel

import androidx.lifecycle.LiveData
import com.example.nijatahmadli.domain.model.Rate

interface ConverterViewModel {

    var baseCurrencyCode: String
    val rates: LiveData<List<Rate>>
    val showLoading: LiveData<Boolean>
    val showError: LiveData<String>

    fun getCurrencies()
}
