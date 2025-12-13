package com.example.sitomanboy.viewmodel

import androidx.lifecycle.ViewModel
import com.example.sitomanboy.models.Repuesto
import com.example.sitomanboy.models.Sucursal

class SitoManBoyViewModel : ViewModel() {
    private val _repuestos = mutableListOf<Repuesto>()
    val repuestos: List<Repuesto> get() = _repuestos

    private val _sucursales = mutableListOf<Sucursal>()
    val sucursales: List<Sucursal> get() = _sucursales

    // Crear repuesto
    fun agregarRepuesto(serie: String, descripcion: String, stock: Int) {
        val id = _repuestos.size + 1
        _repuestos.add(Repuesto(id, serie, descripcion, stock))
    }

    // Crear sucursal
    fun agregarSucursal(codigo: Int, nombre: String, direccion: String) {
        _sucursales.add(Sucursal(codigo, nombre, direccion))
    }

    // Ingresar producto a sucursal (agregar o modificar stock)
    fun ingresarProducto(nombreSucursal: String, serieRepuesto: String, stock: Int) {
        val sucursal = _sucursales.find { it.nombre == nombreSucursal } ?: return
        val repuesto = _repuestos.find { it.serie == serieRepuesto } ?: return
        sucursal.agregarRepuesto(repuesto.copy(stock = stock))
    }

    fun eliminarProducto(nombreSucursal: String, serieRepuesto: String) {
        val sucursal = _sucursales.find { it.nombre == nombreSucursal } ?: return
        sucursal.eliminarRepuesto(serieRepuesto)
    }

    // Buscar stock
    fun buscarStock(nombreOserie: String): List<String> {
        val resultados = mutableListOf<String>()
        _sucursales.forEach { suc ->
            suc.repuestos.filter { it.serie.contains(nombreOserie, true) || it.descripcion.contains(nombreOserie, true) }
                .forEach { rep ->
                    resultados.add("Sucursal: ${suc.nombre}, Repuesto: ${rep.descripcion}, Stock: ${rep.stock}")
                }
        }
        return resultados
    }
}
