package com.scalar.scalar.models.transaction

data class Tx(
        val body: Body,
        val offset: List<Int>
)