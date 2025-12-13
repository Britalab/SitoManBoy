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
import com.example.pruebados.model.Sucursal
import com.example.pruebados.ui.viewmodel.RepuestoViewModel
import java.util.UUID

@Composable
fun PantallaCrearSucursal(controladorNavegacion: NavController, modelo: RepuestoViewModel) {
    var nombre by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text(stringResource(id = R.string.nombre)) }
        )
        OutlinedTextField(
            value = direccion,
            onValueChange = { direccion = it },
            label = { Text(stringResource(id = R.string.direccion)) }
        )
        Button(onClick = {
            val nuevaSucursal = Sucursal(
                codigo = UUID.randomUUID().toString(),
                nombre = nombre,
                direccion = direccion
            )
            modelo.crearSucursal(nuevaSucursal)
            controladorNavegacion.popBackStack()
        }) {
            Text(stringResource(id = R.string.guardar))
        }
    }
}
