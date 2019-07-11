package com.scalar.scalar.models.transaction

data class TransactionModel(
        val amount: Long,
        val fee: Int,
        val height: Int,
        val id: String,
        val lock_height: Int,
        val num_participants: Int,
        val participant_data: List<ParticipantData>,
        val tx: Tx
)