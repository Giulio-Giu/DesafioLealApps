package com.br.testelealapps.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import com.br.testelealapps.R
import com.br.testelealapps.app.TesteApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        configureListeners()

        auth = Firebase.auth
    }

    private fun configureListeners() {
        btn_login.setOnClickListener {
            val email = et_login_email.text.trim().toString()
            val password = et_login_password.text.trim().toString()

            if (android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                    .matches() && password.isNotEmpty()
            ) {
                doLogin(email, password)
            } else {
                MaterialDialog.Builder(this)
                    .title(getString(R.string.error))
                    .content(getString(R.string.login_validation_error_message))
                    .positiveText(getString(R.string.ok))
                    .show()
            }
        }

        tv_login_do_register.setOnClickListener {
            goToRegister()
        }
    }

    public override fun onStart() {
        super.onStart()
    }

    private fun doLogin(email: String, password: String) {

        Log.d(TAG, "signIn:$email")
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithEmail:success")
                    TesteApp.account = auth.currentUser
                    goToMain()
                } else {
                    MaterialDialog.Builder(this@LoginActivity)
                        .title(getString(R.string.error))
                        .content(getString(R.string.login_auth_error_message))
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

    private fun goToRegister() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    companion object {
        private const val TAG = "EmailPassword"
    }
}