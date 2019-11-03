package com.example.nijatahmadli.converter.presentation.converter.view.adapter.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.nijatahmadli.domain.model.Rate

abstract class ConverterItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    abstract fun onBind(rate: Rate, position: Int)
}