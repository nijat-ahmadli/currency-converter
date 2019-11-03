package com.example.nijatahmadli.converter.presentation.converter.viewModel.base


interface ItemViewModelFactory<T> {

    fun createViewModel(): ItemViewModel<T>
}