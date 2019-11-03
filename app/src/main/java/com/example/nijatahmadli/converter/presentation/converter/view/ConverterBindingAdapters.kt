package com.example.nijatahmadli.converter.presentation.converter.view

import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat
import java.util.*

@BindingAdapter("visibleOrGone")
fun View.setVisibleOrGone(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("setFlag")
fun ImageView.setFlag(currencyCode: String?) {
    currencyCode?.let {
        setImageResource(
            resources.getIdentifier(
                "ic_" + it.toLowerCase(),
                "drawable",
                context.packageName
            )
        )
    }
}

@BindingAdapter("setName")
fun TextView.setName(currencyCode: String?) {
    currencyCode?.let {
        text = Currency.getInstance(it).displayName
    }
}

@BindingAdapter("formatValue")
fun EditText.formatValue(value: BigDecimal?) {
    value?.let {
        setText(getFormattedValue(it))
    }
}

fun getFormattedValue(value: BigDecimal): String {
    return NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        maximumFractionDigits = 2
        roundingMode = RoundingMode.FLOOR
    }.format(value)
}