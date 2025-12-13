package com.example.sitomanboy

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sitomanboy.ui.screens.CrearRepuestosActivity
import com.example.sitomanboy.ui.screens.CrearSucursalActivity
import com.example.sitomanboy.ui.screens.IngresarProductoActivity
import com.example.sitomanboy.ui.screens.BuscarStockActivity
import com.example.sitomanboy.ui.theme.SitoManBoyTheme
import com.example.sitomanboy.viewmodel.SitoManBoyViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SitoManBoyTheme {
                val viewModel: SitoManBoyViewModel = viewModel()
                PantallaPrincipal(viewModel)
            }
        }
    }
}

@Composable
fun PantallaPrincipal(viewModel: SitoManBoyViewModel) {
    val context = LocalContext.current  // Necesario para startActivity

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(
            onClick = {
                context.startActivity(Intent(context, CrearRepuestoActivity::class.java))
            },
            modifier = Modifier.padding(bottom = 8.dp)
        ) {
            Text(stringResource(R.string.crear_repuesto))
        }

        Button(
            onClick = {
                context.startActivity(Intent(context, CrearSucursalActivity::class.java))
            },
            modifier = Modifier.padding(bottom = 8.dp)
        ) {
            Text(stringResource(R.string.crear_sucursal))
        }

        Button(
            onClick = {
                context.startActivity(Intent(context, IngresarProductoActivity::class.java))
            },
            modifier = Modifier.padding(bottom = 8.dp)
        ) {
            Text(stringResource(R.string.ingresar_producto))
        }

        Button(
            onClick = {
                context.startActivity(Intent(context, BuscarStockActivity::class.java))
            }
        ) {
            Text(stringResource(R.string.buscar_stock))
        }
    }
}

