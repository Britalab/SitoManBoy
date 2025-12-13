package com.example.pruebados.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.pruebados.model.Repuesto
import com.example.pruebados.model.Sucursal
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RepuestoViewModel : ViewModel() {

    private val _repuestos = MutableStateFlow<List<Repuesto>>(emptyList())
    val repuestos: StateFlow<List<Repuesto>> = _repuestos

    private val _sucursales = MutableStateFlow<List<Sucursal>>(emptyList())
    val sucursales: StateFlow<List<Sucursal>> = _sucursales

    fun crearRepuesto(repuesto: Repuesto) {
        _repuestos.value = _repuestos.value + repuesto
    }

    fun crearSucursal(sucursal: Sucursal) {
        _sucursales.value = _sucursales.value + sucursal
    }

    fun agregarRepuestoASucursal(sucursal: Sucursal, repuesto: Repuesto, stock: Int) {
        val sucursalExistente = _sucursales.value.find { it.codigo == sucursal.codigo }
        sucursalExistente?.let {
            val repuestoExistente = it.obtenerRepuestos().find { r -> r.id == repuesto.id }
            if (repuestoExistente != null) {
                repuestoExistente.stock = stock
            } else {
                repuesto.stock = stock
                it.agregarRepuesto(repuesto)
            }
        }
    }

    fun eliminarRepuestoDeSucursal(sucursal: Sucursal, repuesto: Repuesto) {
        val sucursalExistente = _sucursales.value.find { it.codigo == sucursal.codigo }
        sucursalExistente?.eliminarRepuesto(repuesto.id)
    }

    fun buscarStock(consulta: String): Map<Sucursal, Int> {
        val resultado = mutableMapOf<Sucursal, Int>()
        _sucursales.value.forEach { sucursal ->
            sucursal.obtenerRepuestos().forEach { repuesto ->
                if (repuesto.serie.contains(consulta, ignoreCase = true) || repuesto.descripcion.contains(consulta, ignoreCase = true)) {
                    resultado[sucursal] = repuesto.stock
                }
            }
        }
        return resultado
    }
}
