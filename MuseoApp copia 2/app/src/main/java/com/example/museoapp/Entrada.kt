package com.example.museoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.museoapp.databinding.ActivityEntradaBinding


class Entrada : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityEntradaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle: Bundle? = intent.extras
        val email: String? = bundle?.getString("email")

        binding.nombre.text = email
        //setUp(email)
        setUp()

    }

    private fun setUp(email: String?) {
        title= "Inicio"

        //val textView = findViewById<TextView>(R.id.textView2)
        //textView.text = email

    }

    private fun setUp(){
        title= "Inicio"


    }
}