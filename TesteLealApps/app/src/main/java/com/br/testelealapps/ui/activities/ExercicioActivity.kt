package com.br.testelealapps.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.afollestad.materialdialogs.MaterialDialog
import com.br.testelealapps.R
import com.br.testelealapps.models.Exercicio
import com.br.testelealapps.models.Treino
import kotlinx.android.synthetic.main.activity_exercicio.*

class ExercicioActivity : AppCompatActivity() {

    private lateinit var treino: Treino

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercicio)

        configureListeners()

        treino = intent.getSerializableExtra("treino") as Treino
    }

    private fun configureListeners() {
        btn_exercicio_save.setOnClickListener {
            val name: Int? = et_exercicio_name.text.trim().toString().toInt()
            val comments = et_exercicio_comments.text.toString()
            val imageUrl = et_exercicio_photo_url.text.trim().toString()

            if (name == null || comments.isEmpty() || imageUrl.isEmpty()) {
                MaterialDialog.Builder(this)
                    .title(getString(R.string.error))
                    .content(getString(R.string.exercicio_validation_error_message))
                    .positiveText(getString(R.string.ok))
                    .show()
            } else {
                val exercicio = Exercicio().apply {
                    nome = name
                    observacoes = comments
                    imagem = imageUrl
                }

                treino.listExercicios.add(exercicio)

                MaterialDialog.Builder(this)
                    .title(getString(R.string.success))
                    .content(getString(R.string.exercicio_success_message))
                    .positiveText(getString(R.string.ok))
                    .onPositive { dialog, _ ->
                        dialog.dismiss()
                        finish()
                    }
                    .cancelListener {
                        finish()
                    }
                    .show()
            }
        }
    }
}