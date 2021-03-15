package com.br.testelealapps.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.br.testelealapps.R
import com.br.testelealapps.helpers.SharedPreferencesHelper
import com.br.testelealapps.models.Account
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        doLogin()
    }

    private fun doLogin() {
        val account = Account()
        account.email = et_login_email.text.toString()
        account.password = et_login_password.text.toString()

        //do login e go to main

        SharedPreferencesHelper.saveString(
            this@LoginActivity,
            filename = getString(R.string.account_filename),
            key = getString(R.string.account_key_user_data),
            value = account.getJSON().toString()
        )

        goToMain()
    }

    private fun goToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}