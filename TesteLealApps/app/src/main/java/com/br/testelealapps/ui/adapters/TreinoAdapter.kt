package com.br.testelealapps.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.br.testelealapps.R
import com.br.testelealapps.models.Treino

class TreinoAdapter(private val treinos: ArrayList<Treino>, private val context: Context) :
    RecyclerView.Adapter<TreinoAdapter.ViewHolder>() {

    var clickListener: ClickListener? = null

    interface ClickListener {
        fun onClick(treino: Treino)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_treino_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = treinos[position]

        holder.nome.text = current.nome.toString()
        holder.desc.text = current.descricao

        holder.container.setOnClickListener {
            clickListener?.onClick(current)
        }
    }

    override fun getItemCount(): Int {
        return treinos.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nome: TextView = itemView.findViewById(R.id.tv_treino_nome)
        val desc: TextView = itemView.findViewById(R.id.tv_treino_desc)

        val container: ConstraintLayout = itemView.findViewById(R.id.treino_container)
    }
}