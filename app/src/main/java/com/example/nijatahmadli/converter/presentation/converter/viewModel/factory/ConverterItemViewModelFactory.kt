package com.example.nijatahmadli.converter.presentation.converter.viewModel.factory

import com.example.nijatahmadli.converter.presentation.converter.viewModel.ConverterItemViewModelImpl
import com.example.nijatahmadli.converter.presentation.converter.viewModel.base.ItemViewModel
import com.example.nijatahmadli.converter.presentation.converter.viewModel.base.ItemViewModelFactory
import com.example.nijatahmadli.domain.model.Rate
import javax.inject.Inject

class ConverterItemViewModelFactory @Inject constructor() : ItemViewModelFactory<Rate> {

    override fun createViewModel(): ItemViewModel<Rate> =
        ConverterItemViewModelImpl()
}