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

            if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.isNotEmpty()) {
                doLogin(email, password)
            } else {
                MaterialDialog.Builder(this)
                    .title(getString(R.string.error))
                    .content(getString(R.string.login_validation_error_message))
                    .positiveText(getString(R.string.ok))
                    .show()
            }
        }
    }

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            reload()
        }
    }

    private fun createAccount(email: String, password: String) {
        Log.d(TAG, "createAccount:$email")

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(Companion.TAG, "createUserWithEmail:success")
                    TesteApp.account = auth.currentUser
                    goToMain()
                } else {
                    MaterialDialog.Builder(this@LoginActivity)
                        .title(getString(R.string.error))
                        .content(getString(R.string.register_error_message))
                        .positiveText(getString(R.string.ok))
                        .show()
                }
            }
    }

    private fun reload() {
        auth.currentUser?.let {currentUser ->
            currentUser.reload().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        this@LoginActivity,
                        "Usuário atualizado com sucesso!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Log.e(TAG, "reload", task.exception)
                    Toast.makeText(
                        this@LoginActivity,
                        "Falha ao atualizar o usuário!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun doLogin(email: String, password: String) {

        Log.d(TAG, "signIn:$email")
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithEmail:success")
                    TesteApp.account  = auth.currentUser
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

    companion object {
        private const val TAG = "EmailPassword"
    }
}