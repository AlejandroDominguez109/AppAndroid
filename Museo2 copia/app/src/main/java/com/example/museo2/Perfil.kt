package com.example.museo2

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.Audio.Media
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.example.museo2.databinding.ActivityMenuPrincipalBinding
import com.example.museo2.databinding.ActivityPerfilBinding
import com.example.museo2.fragmet.articulos_frag
import com.example.museo2.fragmet.escaners
import com.example.museo2.fragmet.videos

class Perfil : AppCompatActivity() {

    val escaner_frag = escaners()
    val articulos_frag = articulos_frag()
    val videos_frag = videos()

    private lateinit var binding :ActivityPerfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.contenedor_fragment, videos_frag)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()

        binding.cambiarFoto.setOnClickListener{
            tomarfoto()
        }

    }

    private fun tomarfoto() {


        startForResult.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
    }

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result: ActivityResult ->
        if(result.resultCode == Activity.RESULT_OK){
            val intent = result.data
            val imageBitmap = intent?.extras?.get("data") as Bitmap

            binding.foto.setImageBitmap(imageBitmap)

        }
    }


    fun CambiarFragmentoEscaner(view: View){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.contenedor_fragment, escaner_frag)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()

    }
    fun CambiarFragmentoVideos(view: View){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.contenedor_fragment, videos_frag)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()

    }
    fun CambiarFragmentoArticulos(view: View){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.contenedor_fragment, articulos_frag)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.perfil-> true
            R.id.Articulos -> {
                abrirCat()
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
            R.id.escaner -> {
                abrirEscaner()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun abrirTours() {
        val homeIntent = Intent(this, Tours::class.java).apply {
        }
        startActivity(homeIntent)
    }

    private fun abrirHome() {
        val homeIntent = Intent(this, MenuPrincipal::class.java).apply {
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