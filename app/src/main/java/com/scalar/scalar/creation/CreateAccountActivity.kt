package com.scalar.scalar.creation

import android.content.Intent
import android.os.Bundle
import com.scalar.scalar.BaseActivity
import com.scalar.scalar.R
import kotlinx.android.synthetic.main.activity_create_account.*

class CreateAccountActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        createWalletButton.setOnClickListener {
            startActivity(Intent(this, RecoveryPhraseActivity::class.java))
        }
    }
}
