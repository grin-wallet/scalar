package com.scalar.scalar

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner

class WalletApplication: Application() {

    companion object {
        // Use LocalStoreImpl for SharedPreferences
//        lateinit var wallet: WalletStore

//        var userSession = object : UserSession {
//            var impl = UserSessionImpl()
//
//            override fun getSessionAsset(): SessionAsset { return impl.getSessionAsset() }
//            override fun setSessionAsset(sessionAsset: SessionAsset) {
//                impl.setSessionAsset(sessionAsset)
//                assetSession.postValue(sessionAsset)
//            }
//            override fun getPin(): String? { return impl.getPin() }
//            override fun setPin(pin: String?) {
//                impl.setPin(pin)
//                if(pin != null) {
//                    BalanceRepository.init()
//                }
//            }
//            override fun setMinimumBalance(minimumBalance: MinimumBalance) { impl.setMinimumBalance(minimumBalance) }
//            override fun getMinimumBalance(): MinimumBalance? { return impl.getMinimumBalance() }
//        }
//
//        var assetSession : MutableLiveData<SessionAsset> = MutableLiveData()

        var appReturnedFromBackground = false
    }

    override fun onCreate() {
        super.onCreate()
    }

    private fun setupLifecycleListener() {
        ProcessLifecycleOwner.get().lifecycle.addObserver(lifecycleListener)
    }

    private val lifecycleListener: WalletLifecycleListener by lazy {
        WalletLifecycleListener(applicationContext)
    }
}