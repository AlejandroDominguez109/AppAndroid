package com.example.museo2

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64.decode
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.museo2.databinding.ActivityPerfilBinding
import com.example.museo2.fragmet.articulos_frag
import com.example.museo2.fragmet.escaners
import com.example.museo2.fragmet.videos
import com.google.android.gms.common.util.Base64Utils.decode
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.io.ByteArrayOutputStream
import java.lang.Byte.decode
import java.util.*


class Perfil : AppCompatActivity() {

    val escaner_frag = escaners()
    val articulos_frag = articulos_frag()
    val videos_frag = videos()

    private lateinit var binding :ActivityPerfilBinding

    private val db = FirebaseFirestore.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle:Bundle? = intent.extras
        val email:String? = bundle?.getString("email")
        binding.textViewNombre.text = email


        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.contenedor_fragment, videos_frag)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()

        obtenerfoto(email)

        binding.cambiarFoto.setOnClickListener{
            tomarfoto()
        }
        binding.cerrar.setOnClickListener {
            cerrar()
        }

    }

    private fun cerrar() {
        FirebaseAuth.getInstance().signOut()
        val homeIntent = Intent(this, MainActivity::class.java).apply {
        }
        startActivity(homeIntent)

    }

    private fun obtenerfoto(email: String?) {
        if (email != null) {
            db.collection("users").document(email).get().addOnSuccessListener {

                val imageBytes = android.util.Base64.decode(it.get("imagen").toString(), 0)
                val image = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

                println(image)

                binding.foto.setImageBitmap(image)

            }
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

            guardarbd(imageBitmap)

        }
    }


    private fun guardarbd(imageBitmap: Bitmap) {
        val mail = intent.getStringExtra("email")

        val imagen = BitMapToString(imageBitmap)

        if (mail != null) {
            db.collection("users").document(mail).set(
                hashMapOf("imagen" to imagen)
            )
        }
    }

    fun BitMapToString(bitmap: Bitmap): String {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
        val b = baos.toByteArray()
        return android.util.Base64.encodeToString(b, 0)
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