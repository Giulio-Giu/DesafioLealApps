package com.br.testelealapps.ui.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.br.testelealapps.R
import com.br.testelealapps.helpers.SharedPreferencesHelper
import com.br.testelealapps.models.Account

class SplashActivity : AppCompatActivity() {

    private val delayMillis: Long = 1400

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val json = SharedPreferencesHelper.readString(
            context = this,
            filename = getString(R.string.account_filename),
            key = getString(R.string.account_key_user_data)
        )

        if (json == null) {
            goToLogin()
        } else {
            val account = Account()
            account.parseJSON(json)
            goToMain(account)
        }
    }

    private fun goToMain(account: Account) {
        //do login and go to main
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, delayMillis)
    }

    private fun goToLogin() {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, delayMillis)
    }
}