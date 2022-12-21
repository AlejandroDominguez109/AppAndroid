package com.example.museo2

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.museo2.databinding.ActivityScannerBinding
import com.google.zxing.integration.android.IntentIntegrator

class Scanner : AppCompatActivity() {

    private lateinit var binding: ActivityScannerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityScannerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle:Bundle? = intent.extras
        val email:String? = bundle?.getString("email")


        //llamada metodo escaner nada mas iniciar activity
        escaner()

        binding.BotonEnlace.setOnClickListener {
            abrirEnlace()
        }
        binding.likeBoton.setOnClickListener{
            añadirEscaner()
        }
        binding.AbrirScanner.setOnClickListener {
            escaner()
        }


    }

    private fun añadirEscaner() {
        binding.likeBoton.setBackgroundResource(R.drawable.ic_favorite_red)
    }

    private fun abrirEnlace() {
        //intent.setData(Uri.parse(binding.enlaceEscaner.text.toString()))
        //startActivity(intent)
        if(binding.enlaceEscaner.text.toString().equals("")){
            Toast.makeText(this, "No hay ningun enlace escaneado" , Toast.LENGTH_LONG).show()
        }else{
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(binding.enlaceEscaner.text.toString())
            startActivity(intent)

        }

    }

    private fun escaner(){
        IntentIntegrator(this)
            .setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
            .setPrompt("Escaneando....")
            .setTorchEnabled(false)
            .setBeepEnabled(true)
            .initiateScan()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode,data)

        if(result != null){
            if(result.contents !== null){
                //el text view donde se muestra el enlace.
                binding.enlaceEscaner.text = result.contents

            }else{
                Toast.makeText(this, "cancelado" , Toast.LENGTH_LONG).show()
                }
            }
            super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.escaner-> true
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
            R.id.Home -> {
                abrirHome()
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

    private fun abrirHome() {
        val mail = intent.getStringExtra("email")

        val homeIntent = Intent(this, MenuPrincipal::class.java).apply {
            putExtra("email", mail)
        }
        startActivity(homeIntent)
    }



}