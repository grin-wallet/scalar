package com.scalar.scalar.activities

import android.content.Intent
import android.os.Bundle
import com.scalar.scalar.BaseActivity
import com.scalar.scalar.R
import com.scalar.scalar.creation.CreateAccountActivity
import com.scalar.scalar.importwallet.ImportActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        createWalletButton.setOnClickListener {
            startActivity(Intent(this, CreateAccountActivity::class.java))
        }

        importWalletButton.setOnClickListener {
            startActivity(Intent(this, ImportActivity::class.java))
        }
    }
}
