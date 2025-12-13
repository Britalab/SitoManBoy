package com.example.pruebados.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.example.pruebados.model.Sucursal
import com.example.pruebados.ui.viewmodel.RepuestoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaIngresarProducto(controladorNavegacion: NavController, modelo: RepuestoViewModel) {
    val sucursales by modelo.sucursales.collectAsState()
    val repuestos by modelo.repuestos.collectAsState()

    var sucursalSeleccionada: Sucursal? by remember { mutableStateOf(null) }
    var repuestoSeleccionado: Repuesto? by remember { mutableStateOf(null) }
    var stock by remember { mutableStateOf("") }
    
    var sucursalDesplegado by remember { mutableStateOf(false) }
    var repuestoDesplegado by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ExposedDropdownMenuBox(
            expanded = sucursalDesplegado,
            onExpandedChange = { sucursalDesplegado = !sucursalDesplegado }
        ) {
            OutlinedTextField(
                value = sucursalSeleccionada?.nombre ?: stringResource(id = R.string.seleccione_sucursal),
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = sucursalDesplegado) },
                modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = sucursalDesplegado,
                onDismissRequest = { sucursalDesplegado = false }
            ) {
                sucursales.forEach { sucursal ->
                    DropdownMenuItem(
                        text = { Text(sucursal.nombre) },
                        onClick = {
                            sucursalSeleccionada = sucursal
                            sucursalDesplegado = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        ExposedDropdownMenuBox(
            expanded = repuestoDesplegado,
            onExpandedChange = { repuestoDesplegado = !repuestoDesplegado }
        ) {
            OutlinedTextField(
                value = repuestoSeleccionado?.descripcion ?: stringResource(id = R.string.seleccione_repuesto),
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = repuestoDesplegado) },
                modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = repuestoDesplegado,
                onDismissRequest = { repuestoDesplegado = false }
            ) {
                repuestos.forEach { repuesto ->
                    DropdownMenuItem(
                        text = { Text(repuesto.descripcion) },
                        onClick = {
                            repuestoSeleccionado = repuesto
                            repuestoDesplegado = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = stock,
            onValueChange = { stock = it },
            label = { Text(stringResource(id = R.string.stock)) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (sucursalSeleccionada != null && repuestoSeleccionado != null && stock.isNotEmpty()) {
                    modelo.agregarRepuestoASucursal(sucursalSeleccionada!!, repuestoSeleccionado!!, stock.toInt())
                    controladorNavegacion.popBackStack()
                }
            },
            enabled = sucursalSeleccionada != null && repuestoSeleccionado != null && stock.isNotEmpty()
        ) {
            Text(stringResource(id = R.string.ingresar_modificar_stock))
        }
        
        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (sucursalSeleccionada != null && repuestoSeleccionado != null) {
                    modelo.eliminarRepuestoDeSucursal(sucursalSeleccionada!!, repuestoSeleccionado!!)
                    controladorNavegacion.popBackStack()
                }
            },
            enabled = sucursalSeleccionada != null && repuestoSeleccionado != null,
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
        ) {
            Text(stringResource(id = R.string.eliminar_producto_sucursal))
        }
    }
}
