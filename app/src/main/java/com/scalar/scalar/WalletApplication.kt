package com.scalar.scalar

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner

class WalletApplication: Application() {

    companion object {


        var appReturnedFromBackground = true
    }

    override fun onCreate() {
        super.onCreate()

        setupLifecycleListener()
    }

    private fun setupLifecycleListener() {
        ProcessLifecycleOwner.get().lifecycle.addObserver(lifecycleListener)
    }

    private val lifecycleListener: WalletLifecycleListener by lazy {
        WalletLifecycleListener(applicationContext)
    }
}