package com.scalar.scalar.models.transaction

data class Kernel(
        val excess: List<Int>,
        val excess_sig: List<Int>,
        val features: String,
        val fee: Int,
        val lock_height: Int
)