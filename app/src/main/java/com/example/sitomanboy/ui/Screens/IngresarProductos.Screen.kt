package com.example.sitomanboy.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sitomanboy.viewmodel.SitoManBoyViewModel

@Composable
fun IngresarProductoScreen(viewModel: SitoManBoyViewModel, onBack: () -> Unit) {
    var sucursalSeleccionada by remember { mutableStateOf("") }
    var repuestoSeleccionado by remember { mutableStateOf("") }
    var stock by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // Selector de sucursal (puedes reemplazar con Dropdown si quieres)
        TextField(
            value = sucursalSeleccionada,
            onValueChange = { sucursalSeleccionada = it },
            label = { Text("Sucursal") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Selector de repuesto
        TextField(
            value = repuestoSeleccionado,
            onValueChange = { repuestoSeleccionado = it },
            label = { Text("Repuesto") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Stock
        TextField(
            value = stock,
            onValueChange = { stock = it },
            label = { Text("Stock") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Button(onClick = {
                // Agregar o actualizar stock en la sucursal
                viewModel.ingresarProducto(sucursalSeleccionada, repuestoSeleccionado, stock.toIntOrNull() ?: 0)
                onBack()
            }, modifier = Modifier.padding(end = 8.dp)) {
                Text("Guardar / Actualizar")
            }

            Button(onClick = {
                // Eliminar repuesto de la sucursal
                viewModel.eliminarProducto(sucursalSeleccionada, repuestoSeleccionado)
                onBack()
            }) {
                Text("Eliminar")
            }
        }
    }
}
