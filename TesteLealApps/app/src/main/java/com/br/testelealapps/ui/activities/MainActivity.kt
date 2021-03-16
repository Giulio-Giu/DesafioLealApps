package com.br.testelealapps.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.br.testelealapps.R
import com.br.testelealapps.app.TesteApp
import com.br.testelealapps.models.Treino
import com.br.testelealapps.ui.adapters.TreinoAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var treinos: DatabaseReference

    companion object {
        var listTreino = mutableListOf<Treino>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configureListeners()

        auth = Firebase.auth

        val database = Firebase.database

        treinos = database.getReference("treinos")

        callAdapter()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callAdapter()
    }

    override fun onDestroy() {
        super.onDestroy()
        treinos.setValue(listTreino)
    }

    private fun callAdapter() {
        var list: MutableList<Treino>? = null

        treinos.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.children
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("MAIN", "Failed to read value.", error.toException())
            }
        })


        val adapter = TreinoAdapter(listTreino, this)

        adapter.clickListener = object : TreinoAdapter.ClickListener {

            override fun onClick(treino: Treino) {
                val intent = Intent(this@MainActivity, TreinoDetailActivity::class.java)
                intent.putExtra("treino", treino)
                startActivity(intent)
            }
        }
        recycler_main.adapter = adapter
        recycler_main.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL))
    }

    private fun configureListeners() {
        btn_main_logout.setOnClickListener {
            TesteApp.account = null
            auth.signOut()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        val add: View = findViewById(R.id.iv_btn_add_treino)
        add.setOnClickListener {
            val intent = Intent(this@MainActivity, TreinoActivity::class.java)
            startActivityForResult(intent, 200)
        }
    }
}