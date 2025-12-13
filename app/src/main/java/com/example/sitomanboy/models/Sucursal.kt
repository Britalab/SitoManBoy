package com.example.sitomanboy.models

data class Sucursal(
    val codigo: String,
    val nombre: String,
    val direccion: String,
    private val repuestos: MutableList<Repuesto> = mutableListOf()
) {

    fun agregarRepuesto(repuesto: Repuesto) {
        repuestos.add(repuesto)
    }

    fun eliminarRepuesto(repuesto: Repuesto) {
        repuestos.remove(repuesto)
    }

    fun obtenerRepuestos(): List<Repuesto> {
        return repuestos
    }
}
