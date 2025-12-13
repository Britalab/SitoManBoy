package com.example.pruebados.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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

@Composable
fun PantallaBuscarStock(controladorNavegacion: NavController, modelo: RepuestoViewModel) {
    var consultaBusqueda by remember { mutableStateOf("") }
    var resultadoBusqueda by remember { mutableStateOf<Map<Sucursal, Int>>(emptyMap()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = consultaBusqueda,
            onValueChange = { consultaBusqueda = it },
            label = { Text(stringResource(id = R.string.buscar_por_serie_o_descripcion)) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { 
            resultadoBusqueda = modelo.buscarStock(consultaBusqueda)
        }) {
            Text(stringResource(id = R.string.buscar))
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(resultadoBusqueda.entries.toList()) { (sucursal, stock) ->
                Text("Sucursal: ${sucursal.nombre} - Stock: $stock")
            }
        }
    }
}
