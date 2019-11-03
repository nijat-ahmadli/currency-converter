package com.example.nijatahmadli.converter.presentation.converter.viewModel

import androidx.lifecycle.MutableLiveData
import com.example.nijatahmadli.domain.model.Rate
import java.math.BigDecimal

class ConverterItemViewModelImpl : ConverterItemViewModel {

    override val currencyCode = MutableLiveData<String>()
    override val currencyValue = MutableLiveData<BigDecimal>()

    override fun setModel(model: Rate) {
        currencyCode.value = model.code
        currencyValue.value = model.value
    }
}