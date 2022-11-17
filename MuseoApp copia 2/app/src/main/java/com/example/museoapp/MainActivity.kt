package com.example.museoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.example.museoapp.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {

    private lateinit var  auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth= FirebaseAuth.getInstance()

        binding.botonRegistrar.setOnClickListener {
            val email = binding.Email.text.toString()
            val contraseña = binding.ContraseA.text.toString()
            println(email)
            println(contraseña)
            if (email.isNotEmpty() && contraseña.isNotEmpty()){
                registerUser(email, contraseña)
            }
        }

        binding.BotonAcceder.setOnClickListener {
            val email = binding.Email.text.toString()
            val contraseña = binding.ContraseA.text.toString()
            if (email.isNotEmpty() && contraseña.isNotEmpty()){
                loginUser(email, contraseña)
            }
        }

    }

    private fun registerUser(email: String, contraseña: String){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,contraseña).addOnCompleteListener{
            if (it.isSuccessful){
                showEntrada(email)
            }
            else{
                showAlert()
            }
        }
    }

    private fun loginUser(email: String, contraseña: String){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,contraseña).addOnCompleteListener{
            if (it.isSuccessful){
                showEntrada(email)
            }
            else{
                showAlert()
            }
        }
    }

    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("error")
        builder.setMessage("se ha producido un error")
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showEntrada(email: String){
        val homeIntent = Intent(this, Entrada::class.java).apply {
            putExtra("email", email)
        }
        startActivity(homeIntent)
    }
}