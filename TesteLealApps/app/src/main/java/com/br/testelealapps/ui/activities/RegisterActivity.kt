package com.br.testelealapps.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import com.br.testelealapps.R
import com.br.testelealapps.app.TesteApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        configureListeners()

        auth = Firebase.auth
    }

    private fun configureListeners() {
        btn_register.setOnClickListener {
            val email = et_register_email.text.trim().toString()
            val password = et_register_password.text.trim().toString()

            if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.isNotEmpty()) {
                createAccount(email, password)
            } else {
                MaterialDialog.Builder(this)
                    .title(getString(R.string.error))
                    .content(getString(R.string.login_validation_error_message))
                    .positiveText(getString(R.string.ok))
                    .show()
            }
        }

        btn_back.setOnClickListener {
            finish()
        }
    }

    public override fun onStart() {
        super.onStart()
    }

    private fun createAccount(email: String, password: String) {
        Log.d(TAG, "createAccount:$email")

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "createUserWithEmail:success")
                    TesteApp.account = auth.currentUser
                    goToMain()
                } else {
                    MaterialDialog.Builder(this@RegisterActivity)
                        .title(getString(R.string.error))
                        .content(getString(R.string.register_error_message))
                        .positiveText(getString(R.string.ok))
                        .show()
                }
            }
    }

    private fun goToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    companion object {
        private const val TAG = "EmailPassword"
    }
}