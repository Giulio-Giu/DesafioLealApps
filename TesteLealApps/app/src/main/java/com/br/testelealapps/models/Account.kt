package com.br.testelealapps.models

import org.json.JSONObject

class Account {

    var name: String = ""
    var email: String = ""
    var password: String = ""
    var token: String = ""
    var avatar: String = ""

    fun getJSON(): JSONObject {
        val json = JSONObject()
        json.put("email", email)
        json.put("password", password)
        return json
    }

    fun parseJSON(json: String) {
        val js = JSONObject(json)
        email = js.getString("email")
        password = js.getString("password")
    }
}