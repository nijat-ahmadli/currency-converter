package com.example.nijatahmadli.common.mapper

interface Mapper<I, O> {
    fun map(input: I): O
}