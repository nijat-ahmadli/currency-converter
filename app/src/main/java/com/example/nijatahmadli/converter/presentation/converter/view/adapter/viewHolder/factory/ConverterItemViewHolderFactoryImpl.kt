package com.example.nijatahmadli.converter.presentation.converter.view.adapter.viewHolder.factory

import android.view.View
import com.example.nijatahmadli.converter.presentation.converter.view.adapter.viewHolder.ConverterItemViewHolder
import com.example.nijatahmadli.converter.presentation.converter.view.adapter.viewHolder.ConverterItemViewHolderImpl
import com.example.nijatahmadli.converter.presentation.converter.view.adapter.viewHolder.action.ChangeBaseRateAction
import com.example.nijatahmadli.converter.presentation.converter.view.adapter.viewHolder.action.ValueChangedAction
import com.example.nijatahmadli.converter.presentation.converter.viewModel.base.ItemViewModelFactory
import com.example.nijatahmadli.domain.model.Rate
import javax.inject.Inject

class ConverterItemViewHolderFactoryImpl @Inject constructor(
    private val itemViewModelFactory: ItemViewModelFactory<Rate>,
    private val changeBaseRateAction: ChangeBaseRateAction
) : ConverterItemViewHolderFactory {

    override fun create(
        view: View,
        valueChangedAction: ValueChangedAction
    ): ConverterItemViewHolder {
        return ConverterItemViewHolderImpl(
            view,
            itemViewModelFactory,
            changeBaseRateAction,
            valueChangedAction
        )
    }
}