package com.pedrogv.camisetas.domain.model

data class Camiseta(
    val id: Int,
    val nombre: String,
    val precio: Double,
    val imagenUrl: Int
) {
    override fun toString(): String {
        return "Camiseta(id=$id, nombre='$nombre', precio=$precio, imagenUrl='$imagenUrl')"
    }
}
