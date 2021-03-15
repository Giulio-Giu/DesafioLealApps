package com.br.testelealapps.models

import java.sql.Timestamp

class Treino {
    var nome: Int = 0
    var descricao: String = ""
    var data: Timestamp = Timestamp(System.currentTimeMillis())
}