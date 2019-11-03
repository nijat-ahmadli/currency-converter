package com.example.nijatahmadli.converter.presentation.base

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observeLiveData(lifecycleOwner: LifecycleOwner, action: (T) -> Unit) {
    this.observe(lifecycleOwner, Observer { nullableObject ->
        nullableObject?.let {
            action.invoke(it)
        }
    })
}