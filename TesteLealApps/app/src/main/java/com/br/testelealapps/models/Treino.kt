package com.br.testelealapps.models

import java.io.Serializable
import java.sql.Timestamp

class Treino : Serializable {
    var nome: Int = 0
    var descricao: String = ""
    var data: Timestamp = Timestamp(System.currentTimeMillis())
    var listExercicios: MutableList<Exercicio> = mutableListOf()
}