package com.example.nijatahmadli.converter.presentation.utils

import androidx.lifecycle.LiveData

fun <T> LiveData<T>.test() = LiveDataTestObserver(this)