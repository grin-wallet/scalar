package com.scalar.scalar.interfaces

import com.andrognito.pinlockview.PinLockListener


abstract class OnPinLockCompleteListener : PinLockListener {
    override fun onEmpty() {
        // empty
    }

    override fun onPinChange(pinLength: Int, intermediatePin: String) {
        // empty
    }
}

