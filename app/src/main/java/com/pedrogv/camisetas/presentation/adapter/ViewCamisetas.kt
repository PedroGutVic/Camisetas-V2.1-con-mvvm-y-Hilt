package com.pedrogv.camisetas.presentation.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pedrogv.camisetas.R
import com.pedrogv.camisetas.domain.model.Camiseta

class ViewCamisetas(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val tvNombre: TextView = itemView.findViewById(R.id.txtNombre)
    private val tvPrecio: TextView = itemView.findViewById(R.id.txtPrecio)
    private val ivCamiseta: ImageView = itemView.findViewById(R.id.imgCamiseta)

    fun renderize(camiseta: Camiseta) {
        tvNombre.text = camiseta.nombre
        tvPrecio.text = "$${camiseta.precio}"
        Glide
            .with(itemView.context)
            .load(camiseta.imagenUrl)
            .centerCrop()
            .into(ivCamiseta)
    }
}
