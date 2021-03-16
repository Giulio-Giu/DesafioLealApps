package com.br.testelealapps.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.br.testelealapps.R
import com.br.testelealapps.models.Exercicio
import com.bumptech.glide.Glide

class ExercicioAdapter(private val exercicios: MutableList<Exercicio>, private val context: Context) :
    RecyclerView.Adapter<ExercicioAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_exercicio_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = exercicios[position]

        holder.nome.text = current.nome.toString()
        holder.desc.text = current.observacoes

        val image = holder.img

        Glide.with(context)
            .load(current.imagem)
            .into(image)
    }

    override fun getItemCount(): Int {
        return exercicios.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nome: TextView = itemView.findViewById(R.id.tv_treino_nome)
        var desc: TextView = itemView.findViewById(R.id.tv_treino_desc)
        var img: ImageView = itemView.findViewById(R.id.iv_exercicio_imagem)
    }
}