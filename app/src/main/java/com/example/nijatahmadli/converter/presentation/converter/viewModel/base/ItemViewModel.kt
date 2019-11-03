package com.example.nijatahmadli.converter.presentation.converter.viewModel.base


interface ItemViewModel<in T> {

    fun setModel(model: T)
}