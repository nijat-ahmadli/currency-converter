package com.example.nijatahmadli.converter.presentation.converter.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.nijatahmadli.converter.presentation.converter.view.adapter.viewHolder.ConverterItemViewHolder
import com.example.nijatahmadli.domain.model.Rate
import java.math.BigDecimal

abstract class ConverterRecyclerViewAdapter : RecyclerView.Adapter<ConverterItemViewHolder>() {

    abstract var multiplier: BigDecimal
    abstract val currencyCodes: List<String>

    abstract fun setRates(rates: List<Rate>)

    abstract fun setBase(position: Int)

    abstract fun updateValues(value: String)
}