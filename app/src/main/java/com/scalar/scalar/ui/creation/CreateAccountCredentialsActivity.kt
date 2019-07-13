package com.scalar.scalar.ui.creation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.scalar.scalar.R
import com.scalar.scalar.ui.activities.BaseActivity
import kotlinx.android.synthetic.main.activity_create_account.*
import kotlinx.android.synthetic.main.activity_create_account_credentials.*
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class CreateAccountCredentialsActivity : BaseActivity() {
    private var TAG = "CreateAccountCredentialsActivity"
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account_credentials)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        createAccountButton.setOnClickListener {
            var createAccountEmail = findViewById(R.id.createAccountEmail) as EditText
            var createAccountPassword = findViewById(R.id.createAccountPassword) as EditText
            var email = createAccountEmail.text.toString()
            var password = createAccountPassword.text.toString()
            Log.d(TAG, "my Message" + createAccountEmail.text + "+" + createAccountPassword.text)
            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success")
                            val user = auth.currentUser
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.exception)
                            Toast.makeText(baseContext, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show()
                        }
                    }
            startActivity(Intent(this, RecoveryPhraseActivity::class.java))
        }
    }
}