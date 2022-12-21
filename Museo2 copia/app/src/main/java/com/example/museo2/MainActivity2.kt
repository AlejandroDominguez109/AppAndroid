package com.example.museo2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.museo2.adapter.articulosAdapter
import com.example.museo2.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {


    private lateinit var binding: ActivityMain2Binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle:Bundle? = intent.extras
        val email:String? = bundle?.getString("email")


        initRecycledView()


    }

    fun initRecycledView(){

        val manager = LinearLayoutManager(this)
        val decoration = DividerItemDecoration(this,manager.orientation)
        binding.recyclerArticulos.layoutManager = manager
        binding.recyclerArticulos.adapter  = articulosAdapter(articulosprovider.articulosLista)

        binding.recyclerArticulos.addItemDecoration(decoration)


    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.Articulos-> true
            R.id.Home -> {
                abrirHome()
                true
            }
            R.id.perfil -> {
                abrirPerfil()
                true
            }
            R.id.Tours -> {
                abrirTours()
                true
            }
            R.id.escaner -> {
                abrirEscaner()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun abrirPerfil() {
        val mail = intent.getStringExtra("email")

        val homeIntent = Intent(this, Perfil::class.java).apply {
            putExtra("email", mail)
        }
        startActivity(homeIntent)
    }

    private fun abrirTours() {
        val mail = intent.getStringExtra("email")

        val homeIntent = Intent(this, Tours::class.java).apply {
            putExtra("email", mail)
        }
        startActivity(homeIntent)
    }

    private fun abrirHome() {
        val mail = intent.getStringExtra("email")

        val homeIntent = Intent(this, MenuPrincipal::class.java).apply {
            putExtra("email", mail)
        }
        startActivity(homeIntent)
    }

    private fun abrirEscaner() {
        val mail = intent.getStringExtra("email")

        val homeIntent = Intent(this, Scanner::class.java).apply {
            putExtra("email", mail)
        }
        startActivity(homeIntent)
    }
}