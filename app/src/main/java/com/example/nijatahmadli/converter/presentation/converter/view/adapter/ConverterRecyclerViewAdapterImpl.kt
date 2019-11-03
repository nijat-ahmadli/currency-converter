package com.example.nijatahmadli.converter.presentation.converter.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.nijatahmadli.converter.R
import com.example.nijatahmadli.converter.presentation.converter.view.adapter.viewHolder.ConverterItemViewHolder
import com.example.nijatahmadli.converter.presentation.converter.view.adapter.viewHolder.factory.ConverterItemViewHolderFactory
import com.example.nijatahmadli.domain.model.Rate
import java.math.BigDecimal
import javax.inject.Inject


class ConverterRecyclerViewAdapterImpl @Inject constructor(
    private val baseRate: Rate,
    private val converterItemViewHolderFactory: ConverterItemViewHolderFactory
) : ConverterRecyclerViewAdapter() {

    override var multiplier = BigDecimal(1.0)
    override val currencyCodes = ArrayList<String>()
    private val ratesMap = hashMapOf(baseRate.code to baseRate.value)

    override fun setRates(newRates: List<Rate>) {
        ratesMap.putAll(newRates.map { it.code to it.value })

        if (currencyCodes.isEmpty()) {
            currencyCodes.add(baseRate.code)
            currencyCodes.addAll(newRates.map { it.code })
        }

        notifyItemRangeChanged(1, itemCount)
    }

    override fun setBase(position: Int) {
        val newBaseRate = currencyCodes[position]
        currencyCodes.removeAt(position)
        currencyCodes.add(0, newBaseRate)
        notifyItemMoved(position, 0)
        multiplier = multiplier.multiply(ratesMap[newBaseRate]!!)
    }

    override fun updateValues(newMultiplier: String) {
        multiplier = if (newMultiplier.isNotEmpty()) BigDecimal(newMultiplier) else BigDecimal(0.0)
        notifyItemRangeChanged(1, itemCount)
    }

    override fun getItemCount() = currencyCodes.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConverterItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(
            R.layout.converter_list_item,
            parent,
            false
        )
        return converterItemViewHolderFactory.create(view, ::updateValues)
    }

    override fun onBindViewHolder(holder: ConverterItemViewHolder, position: Int) {
        val currency = currencyCodes[position]
        holder.onBind(Rate(currency, ratesMap[currency]!!.multiply(multiplier)), position)
    }
}