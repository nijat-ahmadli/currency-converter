package com.example.nijatahmadli.converter.presentation.converter.view.adapter.viewHolder.factory

import android.view.View
import com.example.nijatahmadli.converter.presentation.converter.view.adapter.viewHolder.ConverterItemViewHolder
import com.example.nijatahmadli.converter.presentation.converter.view.adapter.viewHolder.action.ValueChangedAction

interface ConverterItemViewHolderFactory {

    fun create(view: View, valueChangedAction: ValueChangedAction): ConverterItemViewHolder
}