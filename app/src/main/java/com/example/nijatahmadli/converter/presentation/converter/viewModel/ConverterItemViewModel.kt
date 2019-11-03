package com.example.nijatahmadli.converter.presentation.converter.viewModel

import androidx.lifecycle.LiveData
import com.example.nijatahmadli.converter.presentation.converter.viewModel.base.ItemViewModel
import com.example.nijatahmadli.domain.model.Rate
import java.math.BigDecimal

interface ConverterItemViewModel : ItemViewModel<Rate> {

    val currencyCode: LiveData<String>
    val currencyValue: LiveData<BigDecimal>
}