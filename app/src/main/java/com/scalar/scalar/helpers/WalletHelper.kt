package com.scalar.scalar.helpers

import android.content.Context
import android.content.Intent
import androidx.fragment.app.FragmentActivity
import com.scalar.scalar.ui.activities.LoginActivity

class WalletHelper {
    companion object {
        fun wipeAndRestart(activity : FragmentActivity) {
            wipe()
            restart(activity)
        }

        fun doesWalletExist(context: Context) : Boolean {
            return SharedPreferencesHelper(context).doesPinExist
        }

        fun wipe() {
        }

        private fun restart(activity : FragmentActivity){
            val intent = Intent(activity, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            activity.startActivity(intent)
            activity.finish()
        }

    }
}