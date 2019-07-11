package com.scalar.scalar.models.transaction

data class Output(
        val commit: List<Int>,
        val features: String,
        val proof: List<Int>
)