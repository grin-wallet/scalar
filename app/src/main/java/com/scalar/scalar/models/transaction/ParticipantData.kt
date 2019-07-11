package com.scalar.scalar.models.transaction

data class ParticipantData(
        val id: Int,
        val message: Any,
        val message_sig: Any,
        val part_sig: Any,
        val public_blind_excess: List<Int>,
        val public_nonce: List<Int>
)