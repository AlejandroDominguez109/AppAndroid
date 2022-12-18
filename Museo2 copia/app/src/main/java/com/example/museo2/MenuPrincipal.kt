package com.example.museo2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.view.Menu
import android.view.MenuItem
import com.example.museo2.databinding.ActivityMenuPrincipalBinding
import com.example.museo2.databinding.ActivityScannerBinding

class MenuPrincipal : AppCompatActivity() {

    private lateinit var binding: ActivityMenuPrincipalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMenuPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle:Bundle? = intent.extras
        val email:String? = bundle?.getString("email")

        setup()

        binding.botonQR.setOnClickListener { abrirEscaner() }
        binding.botonCategorias.setOnClickListener { abrirCat() }
        binding.botonTours.setOnClickListener { abrirTours() }


    }

    private fun setup() {
        val mail = intent.getStringExtra("email")
        binding.nombre.text = mail
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.Home-> true
            R.id.Articulos -> {
                abrirCat()
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