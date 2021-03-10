package com.br.testelealapps.app

import android.app.Application
import com.br.testelealapps.models.Account

class TesteApp() : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {

        var account: Account? = null

        private var instance: TesteApp? = null

        fun getInstance(): TesteApp? {
            return instance
        }
    }
}