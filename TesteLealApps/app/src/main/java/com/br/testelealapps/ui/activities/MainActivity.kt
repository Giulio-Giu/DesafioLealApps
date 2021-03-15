package com.br.testelealapps.ui.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.br.testelealapps.R
import com.br.testelealapps.models.Treino
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var sharedPreferencesGlobal: SharedPreferences? = null
    companion object {
        var listTreino = mutableListOf<Treino>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



//        val adapter = CarAdapter(cars(), this)
//
//        adapter.clickListener = object : CarAdapter.ClickListener {
//
//            override fun onClick(car: Car) {
//                val intent = Intent(this@MainActivity, CarDetailActivity::class.java)
//                intent.putExtra("car", car)
//                startActivity(intent)
//            }
//        }
//        recyclerView.adapter = adapter

        recycler_main.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL))

    }
}