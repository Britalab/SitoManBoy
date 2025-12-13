package com.example.sitomanboy.ui.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sitomanboy.models.Repuesto
import com.example.sitomanboy.viewmodel.SitoManBoyViewModel

@Composable
fun CrearRepuestoScreen(viewModel: SitoManBoyViewModel, onBack: () -> Unit) {
    val id = remember { mutableStateOf("") }
    val serie = remember { mutableStateOf("") }
    val descripcion = remember { mutableStateOf("") }
    val stock = remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = id.value,
            onValueChange = { id.value = it },
            label = { Text("ID") }
        )
        TextField(
            value = serie.value,
            onValueChange = { serie.value = it },
            label = { Text("Serie") }
        )
        TextField(
            value = descripcion.value,
            onValueChange = { descripcion.value = it },
            label = { Text("Descripción") }
        )
        TextField(
            value = stock.value,
            onValueChange = { stock.value = it },
            label = { Text("Stock") }
        )
        Button(onClick = {
            val repuesto = Repuesto(
                id.value.toInt(),
                serie.value,
                descripcion.value,
                stock.value.toInt()
            )
            viewModel.agregarRepuesto(repuesto)
            onBack() // regresar a la pantalla principal
        }) {
            Text("Guardar")
        }
    }
}
