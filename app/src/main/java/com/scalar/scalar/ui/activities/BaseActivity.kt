package com.scalar.scalar.ui.activities

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.scalar.scalar.WalletApplication
import com.scalar.scalar.helpers.WalletHelper

abstract class BaseActivity : AppCompatActivity() {
    private val VERIFY_PIN_REQUEST : Int = 0x01

    override fun onResume() {
        super.onResume()
        val doesWalletExist = WalletHelper.doesWalletExist(applicationContext)
        if (WalletApplication.appReturnedFromBackground){
            WalletApplication.appReturnedFromBackground = false

            val intent = Intent(applicationContext, PinActivity::class.java)
            startActivityForResult(intent, VERIFY_PIN_REQUEST)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == VERIFY_PIN_REQUEST) {
            when(resultCode) {
                Activity.RESULT_OK -> Log.d("Pin accepted", "Pin success!")
                Activity.RESULT_CANCELED -> finish()
            }
        }
    }
}
