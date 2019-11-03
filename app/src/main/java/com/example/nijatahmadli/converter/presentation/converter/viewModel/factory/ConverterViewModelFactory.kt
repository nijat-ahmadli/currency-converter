package com.example.nijatahmadli.converter.presentation.converter.viewModel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nijatahmadli.converter.presentation.converter.viewModel.ConverterViewModelImpl
import com.example.nijatahmadli.domain.model.Rate
import com.example.nijatahmadli.domain.usecase.GetCurrenciesUseCase
import javax.inject.Inject

class ConverterViewModelFactory @Inject constructor(
    private val baseRate: Rate,
    private val getCurrenciesUseCase: GetCurrenciesUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ConverterViewModelImpl(
            baseRate,
            getCurrenciesUseCase
        ) as T
    }
}