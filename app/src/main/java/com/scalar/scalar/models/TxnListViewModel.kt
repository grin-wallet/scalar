package com.scalar.scalar.models

enum class TransactionType(val value : String) {
    SENT("Sent"), RECEIVED("Received")
}

/**
 * Class which provides a model for TxnListViewModel
 * @constructor Sets all properties of the TxnListViewModel
 * @property type the type of account effect as shown in EffectType
 * @property createdAt the time at which the effect was created
 * @property amount the amount of asset transacted if there was a transaction
 */
data class TxnListViewModel (var type: TransactionType, var createdAt: String, var amount: String)
