package com.example.nijatahmadli.common.mapper

interface MapperWithNullableInput<I, O> {
    fun map(input: I?): O
}