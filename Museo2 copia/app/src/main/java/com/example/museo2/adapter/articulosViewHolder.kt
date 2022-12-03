package com.example.museo2.adapter

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.museo2.articulos
import com.example.museo2.databinding.ItemArticulosBinding

class articulosViewHolder (view: View) : RecyclerView.ViewHolder(view ){

    val binding = ItemArticulosBinding.bind(view)


    fun render(articulosModel: articulos) {
        binding.idArticulo.text = articulosModel.titulo
        Glide.with(binding.idImagen.context).load(articulosModel.foto).into(binding.idImagen)


        itemView.setOnClickListener() {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(articulosModel.url)

            itemView.context.startActivity(intent)

            //this.startService(intent)
        }
    }
}