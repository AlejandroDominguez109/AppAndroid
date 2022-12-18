package com.example.museo2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

class Tours : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tours)

        val bundle:Bundle? = intent.extras
        val email:String? = bundle?.getString("email")

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.Tours-> true
            R.id.Articulos -> {
                abrirCat()
                true
            }
            R.id.perfil -> {
                abrirPerfil()
                true
            }
            R.id.Home -> {
                abrirHome()
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

    private fun abrirHome() {
        val mail = intent.getStringExtra("email")

        val homeIntent = Intent(this, MenuPrincipal::class.java).apply {
            putExtra("email", mail)
        }
        startActivity(homeIntent)
    }

    private fun abrirCat() {
        val mail = intent.getStringExtra("email")

        val homeIntent = Intent(this, MainActivity2::class.java).apply {
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