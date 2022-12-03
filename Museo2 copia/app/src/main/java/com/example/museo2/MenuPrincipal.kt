package com.example.museo2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.museo2.databinding.ActivityMenuPrincipalBinding
import com.example.museo2.databinding.ActivityScannerBinding

class MenuPrincipal : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMenuPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.botonQR.setOnClickListener { abrirEscaner() }
        binding.botonCategorias.setOnClickListener { abrirCat() }
        binding.botonTours.setOnClickListener { abrirTours() }
    }

    private fun abrirTours() {
        val homeIntent = Intent(this, Tours::class.java).apply {
        }
        startActivity(homeIntent)
    }

    private fun abrirCat() {
        val homeIntent = Intent(this, MainActivity2::class.java).apply {
        }
        startActivity(homeIntent)
    }

    private fun abrirEscaner() {
        val homeIntent = Intent(this, Scanner::class.java).apply {
        }
        startActivity(homeIntent)
    }
}