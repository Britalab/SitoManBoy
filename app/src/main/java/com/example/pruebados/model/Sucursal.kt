package com.example.pruebados.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Sucursal(
    val codigo: String,
    val nombre: String,
    val direccion: String,
    private val repuestos: MutableList<Repuesto> = mutableListOf()
) : Parcelable {

    fun agregarRepuesto(repuesto: Repuesto) {
        repuestos.add(repuesto)
    }

    fun eliminarRepuesto(idRepuesto: String) {
        repuestos.removeAll { it.id == idRepuesto }
    }

    fun obtenerRepuestos(): List<Repuesto> {
        return repuestos.toList()
    }
}
