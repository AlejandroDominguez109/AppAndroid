package com.example.museo2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.museo2.R
import com.example.museo2.articulos

class articulosAdapter(private val articulosLista:List<articulos>) : RecyclerView.Adapter<articulosViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): articulosViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return articulosViewHolder(layoutInflater.inflate(R.layout.item_articulos,parent,false))
    }

    override fun onBindViewHolder(holder: articulosViewHolder, position: Int) {
        val item = articulosLista[position]
        holder.render(item)

    }

    override fun getItemCount(): Int = articulosLista.size

}