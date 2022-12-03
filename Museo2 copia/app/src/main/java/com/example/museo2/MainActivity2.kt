package com.example.museo2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        initRecycledView()

    }

    fun initRecycledView(){

        val manager = LinearLayoutManager(this)
        val decoration = DividerItemDecoration(this,manager.orientation)
        binding.recyclerArticulos.layoutManager = manager
        binding.recyclerArticulos.adapter  = articulosAdapter(articulosprovider.articulosLista)

        binding.recyclerArticulos.addItemDecoration(decoration)


    }
}