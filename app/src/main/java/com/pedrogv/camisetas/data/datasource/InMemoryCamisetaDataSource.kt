package com.pedrogv.camisetas.data.datasource

import com.pedrogv.camisetas.R
import com.pedrogv.camisetas.domain.model.Camiseta
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InMemoryCamisetaDataSource @Inject constructor() {
    private val items: MutableList<Camiseta> = mutableListOf(
        Camiseta(1, "Camiseta Basica Blanca", 15.99, R.drawable.camiseta),
        Camiseta(2, "Camiseta Negra Clasica", 17.49, R.drawable.camiseta),
        Camiseta(3, "Camiseta Azul Marino", 18.99, R.drawable.camiseta),
        Camiseta(4, "Camiseta Roja Deportiva", 20.0, R.drawable.camiseta),
        Camiseta(5, "Camiseta Verde Militar", 19.5, R.drawable.camiseta),
        Camiseta(6, "Camiseta Amarilla Casual", 16.75, R.drawable.camiseta),
        Camiseta(7, "Camiseta Gris Melange", 18.25, R.drawable.camiseta),
        Camiseta(8, "Camiseta Estampada Retro", 21.0, R.drawable.camiseta),
        Camiseta(9, "Camiseta de Manga Larga Negra", 22.5, R.drawable.camiseta),
        Camiseta(10, "Camiseta Deportiva Blanca", 20.99, R.drawable.camiseta)
    )

    fun getAll(): List<Camiseta> {
        return items.toList()
    }

    fun insert(camiseta: Camiseta) {
        val newId = (items.maxOfOrNull { it.id } ?: 0) + 1
        items.add(camiseta.copy(id = newId))
    }

    fun update(camiseta: Camiseta) {
        val index = items.indexOfFirst { it.id == camiseta.id }
        if (index != -1) {
            items[index] = camiseta
        }
    }

    fun findById(id: Int): Camiseta? {
        return items.find { it.id == id }
    }

    fun deleteById(id: Int) {
        val index = items.indexOfFirst { it.id == id }
        if (index != -1) {
            items.removeAt(index)
        }
    }
}
