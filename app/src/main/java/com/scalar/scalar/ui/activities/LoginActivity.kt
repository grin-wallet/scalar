package com.scalar.scalar.ui.activities

import android.content.Intent
import android.os.Bundle
import com.scalar.scalar.R
import com.scalar.scalar.ui.creation.CreateAccountActivity
import com.scalar.scalar.ui.importwallet.ImportActivity
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
