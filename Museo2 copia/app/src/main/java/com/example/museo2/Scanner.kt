package com.example.museo2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.museo2.databinding.ActivityScannerBinding
import com.google.zxing.integration.android.IntentIntegrator

class Scanner : AppCompatActivity() {

    private lateinit var binding: ActivityScannerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityScannerBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //llamada metodo escaner nada mas iniciar activity
        escaner()


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

}