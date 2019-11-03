package com.example.nijatahmadli.converter.presentation.converter.view.adapter.viewHolder

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import com.example.nijatahmadli.converter.databinding.ConverterListItemBinding
import com.example.nijatahmadli.converter.presentation.converter.view.adapter.viewHolder.action.ChangeBaseRateAction
import com.example.nijatahmadli.converter.presentation.converter.view.adapter.viewHolder.action.ValueChangedAction
import com.example.nijatahmadli.converter.presentation.converter.viewModel.ConverterItemViewModel
import com.example.nijatahmadli.converter.presentation.converter.viewModel.base.ItemViewModelFactory
import com.example.nijatahmadli.domain.model.Rate

class ConverterItemViewHolderImpl(
    private val view: View,
    itemViewModelFactory: ItemViewModelFactory<Rate>,
    private val changeBaseRateAction: ChangeBaseRateAction,
    private val valueChangedAction: ValueChangedAction
) : ConverterItemViewHolder(view) {

    private var binding: ConverterListItemBinding? = null

    private val itemViewModel: ConverterItemViewModel =
        itemViewModelFactory.createViewModel() as ConverterItemViewModel

    init {
        binding = DataBindingUtil.bind(view)
        binding?.viewModel = itemViewModel
        binding?.executePendingBindings()
    }

    override fun onBind(rate: Rate, position: Int) {
        binding?.lifecycleOwner = view.context as FragmentActivity
        itemViewModel.setModel(rate)
        setOnFocusChangeListener()
        setTextChangedListener()
    }

    private fun setOnFocusChangeListener() {
        binding?.currencyValue?.setOnFocusChangeListener { _, _ ->
            if (adapterPosition != 0) {
                changeBaseRateAction(adapterPosition)
            }
        }
    }

    private fun setTextChangedListener() {
        binding?.currencyValue?.let {
            it.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                    if (adapterPosition == 0) {
                        if (it.hasFocus()) {
                            valueChangedAction(p0.toString())
                        }
                    }
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            })
        }
    }
}