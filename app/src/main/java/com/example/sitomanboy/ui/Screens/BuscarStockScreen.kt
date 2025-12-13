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
fun BuscarStockScreen(viewModel: SitoManBoyViewModel, onBack: () -> Unit) {
    val serie = remember { mutableStateOf("") }
    val resultados = remember { mutableStateOf(listOf<Pair<Sucursal, Int>>()) }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = serie.value,
            onValueChange = { serie.value = it },
            label = { Text("Serie del Repuesto") }
        )

        Button(onClick = {
            resultados.value = viewModel.buscarRepuesto(serie.value)
        }) { Text("Buscar") }

        resultados.value.forEach { (sucursal, stock) ->
            Text("${sucursal.nombre} (${sucursal.direccion}) - Stock: $stock")
        }
    }
}
