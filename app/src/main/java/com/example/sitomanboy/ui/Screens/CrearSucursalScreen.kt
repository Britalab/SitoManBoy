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
import com.example.sitomanboy.models.Sucursal
import com.example.sitomanboy.viewmodel.SitoManBoyViewModel

@Composable
fun CrearSucursalScreen(viewModel: SitoManBoyViewModel, onBack: () -> Unit) {
    val codigo = remember { mutableStateOf("") }
    val nombre = remember { mutableStateOf("") }
    val direccion = remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = codigo.value,
            onValueChange = { codigo.value = it },
            label = { Text("Código") }
        )
        TextField(
            value = nombre.value,
            onValueChange = { nombre.value = it },
            label = { Text("Nombre") }
        )
        TextField(
            value = direccion.value,
            onValueChange = { direccion.value = it },
            label = { Text("Dirección") }
        )
        Button(onClick = {
            val sucursal = Sucursal(
                codigo.value.toInt(),
                nombre.value,
                direccion.value
            )
            viewModel.agregarSucursal(sucursal)
            onBack()
        }) {
            Text("Guardar")
        }
    }
}
