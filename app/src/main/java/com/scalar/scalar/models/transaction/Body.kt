package com.scalar.scalar.models.transaction

data class Body(
        val inputs: List<Input>,
        val kernels: List<Kernel>,
        val outputs: List<Output>
)