package com.br.testelealapps.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.br.testelealapps.R
import com.br.testelealapps.models.Exercicio
import com.br.testelealapps.models.Treino
import com.br.testelealapps.ui.adapters.ExercicioAdapter
import kotlinx.android.synthetic.main.activity_treino_detail.*

class TreinoDetailActivity : AppCompatActivity() {

    private lateinit var treino: Treino

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_treino_detail)

        configureListeners()

        treino = intent.getSerializableExtra("treino") as Treino

        tv_treino_detail_title.text =
            getString(R.string.treino_detail_title, treino.nome.toString())

        callAdapter()
    }

    override fun onResume() {
        super.onResume()
        callAdapter()
    }

    private fun configureListeners() {
        val addExercicio: View = findViewById(R.id.iv_treino_detail_add_exercicio)
        addExercicio.setOnClickListener {
            val intent = Intent(this, ExercicioActivity::class.java)
            intent.putExtra("treino", treino)
            startActivity(intent)
        }
    }

    private fun callAdapter() {
        val adapter = ExercicioAdapter(treino.listExercicios, this)
        recycler_treino_detail.adapter = adapter
        recycler_treino_detail.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.HORIZONTAL
            )
        )
    }
}