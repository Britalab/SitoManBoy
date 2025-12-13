package com.example.pruebados.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pruebados.R
import com.example.pruebados.model.Repuesto
import com.example.pruebados.ui.viewmodel.RepuestoViewModel
import java.util.UUID

@Composable
fun PantallaCrearRepuesto(controladorNavegacion: NavController, modelo: RepuestoViewModel) {
    var serie by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var stock by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = serie,
            onValueChange = { serie = it },
            label = { Text(stringResource(id = R.string.serie)) }
        )
        OutlinedTextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text(stringResource(id = R.string.descripcion)) }
        )
        OutlinedTextField(
            value = stock,
            onValueChange = { stock = it },
            label = { Text(stringResource(id = R.string.stock)) }
        )
        Button(onClick = {
            val nuevoRepuesto = Repuesto(
                id = UUID.randomUUID().toString(),
                serie = serie,
                descripcion = descripcion,
                stock = stock.toIntOrNull() ?: 0
            )
            modelo.crearRepuesto(nuevoRepuesto)
            controladorNavegacion.popBackStack()
        }) {
            Text(stringResource(id = R.string.guardar))
        }
    }
}
