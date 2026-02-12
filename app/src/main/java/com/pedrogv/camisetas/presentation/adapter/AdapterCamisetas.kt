package com.pedrogv.camisetas.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.pedrogv.camisetas.R
import com.pedrogv.camisetas.domain.model.Camiseta

typealias OnEditClickListener = (camisetaId: Int) -> Unit

typealias OnDeleteClickListener = (camisetaId: Int) -> Unit

typealias OnItemClickListener = (position: Int) -> Unit

class AdapterCamisetas(
    var listTshit: MutableList<Camiseta>,
    private val onEditClick: OnEditClickListener,
    private val onDeleteClick: OnDeleteClickListener,
    private val onItemClick: OnItemClickListener
) : RecyclerView.Adapter<ViewCamisetas>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewCamisetas {
        val layoutInflater = LayoutInflater.from(parent.context)
        val layoutItemCamiseta = R.layout.card_view
        return ViewCamisetas(layoutInflater.inflate(layoutItemCamiseta, parent, false))
    }

    override fun onBindViewHolder(holder: ViewCamisetas, position: Int) {
        val camiseta = listTshit[position]
        holder.renderize(camiseta)

        holder.itemView.setOnClickListener {
            onItemClick(position)
        }

        holder.itemView.findViewById<Button>(R.id.delete).setOnClickListener {
            onDeleteClick(camiseta.id)
        }

        holder.itemView.findViewById<Button>(R.id.edit).setOnClickListener {
            onEditClick(camiseta.id)
        }
    }

    override fun getItemCount(): Int = listTshit.size

    fun updateList(newList: MutableList<Camiseta>) {
        listTshit = newList
        notifyDataSetChanged()
    }
}
