package com.pedrogv.camisetas.domain.repository

import com.pedrogv.camisetas.domain.model.Camiseta

interface CamisetaRepository {
    fun getCamisetas(): List<Camiseta>
    fun insertCamiseta(camiseta: Camiseta)
    fun updateCamiseta(camiseta: Camiseta)
    fun findCamisetaById(id: Int): Camiseta?
    fun deleteCamiseta(id: Int)
}
