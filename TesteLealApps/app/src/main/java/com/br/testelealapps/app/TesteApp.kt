package com.br.testelealapps.app

import android.app.Application
import com.google.firebase.auth.FirebaseUser

class TesteApp() : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {

        var account: FirebaseUser? = null

        private var instance: TesteApp? = null

        fun getInstance(): TesteApp? {
            return instance
        }
    }
}