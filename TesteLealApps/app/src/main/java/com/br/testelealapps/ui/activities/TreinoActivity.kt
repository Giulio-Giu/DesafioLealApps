package com.br.testelealapps.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import com.br.testelealapps.R
import com.br.testelealapps.models.Treino
import kotlinx.android.synthetic.main.activity_treino.*

class TreinoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_treino)

        configureListeners()
    }

    private fun configureListeners() {
        btn_treino_save.setOnClickListener {
            val name: Int? = et_treino_name.text.trim().toString().toInt()
            val description = et_treino_description.text.toString()

            if (name == null || description.isEmpty()) {
                MaterialDialog.Builder(this)
                    .title(getString(R.string.error))
                    .content(getString(R.string.treino_validation_error_message))
                    .positiveText(getString(R.string.ok))
                    .show()
            } else {
                val treino = Treino().apply {
                    nome = name
                    descricao = description
                }

                MainActivity.listTreino.add(treino)

                MaterialDialog.Builder(this)
                    .title(getString(R.string.success))
                    .content(getString(R.string.treino_success_message))
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