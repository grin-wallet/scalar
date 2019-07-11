package com.scalar.scalar.models.transaction

data class Input(
        val commit: List<Int>,
        val features: String
)