package com.example.pruebados

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.os.LocaleListCompat
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pruebados.ui.screens.PantallaBuscarStock
import com.example.pruebados.ui.screens.PantallaCrearRepuesto
import com.example.pruebados.ui.screens.PantallaCrearSucursal
import com.example.pruebados.ui.screens.PantallaIngresarProducto
import com.example.pruebados.ui.theme.PruebaDosTheme
import com.example.pruebados.ui.viewmodel.RepuestoViewModel

class MainActivity : AppCompatActivity() {
    private val repuestoViewModel: RepuestoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PruebaDosTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    NavegacionApp(repuestoViewModel)
                }
            }
        }
    }
}

@Composable
fun NavegacionApp(modelo: RepuestoViewModel) {
    val controladorNavegacion = rememberNavController()
    NavHost(navController = controladorNavegacion, startDestination = "principal") {
        composable("principal") { PantallaPrincipal(controladorNavegacion) }
        composable("crear_repuesto") { PantallaCrearRepuesto(controladorNavegacion, modelo) }
        composable("crear_sucursal") { PantallaCrearSucursal(controladorNavegacion, modelo) }
        composable("ingresar_producto") { PantallaIngresarProducto(controladorNavegacion, modelo) }
        composable("buscar_stock") { PantallaBuscarStock(controladorNavegacion, modelo) }
    }
}

@SuppressLint("LocalContextConfigurationRead")
@Composable
fun PantallaPrincipal(controladorNavegacion: NavController) {
    val context = LocalContext.current
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { controladorNavegacion.navigate("crear_repuesto") }) {
                Text(text = stringResource(id = R.string.crear_repuesto))
            }
            Button(onClick = { controladorNavegacion.navigate("crear_sucursal") }) {
                Text(text = stringResource(id = R.string.crear_sucursal))
            }
            Button(onClick = { controladorNavegacion.navigate("ingresar_producto") }) {
                Text(text = stringResource(id = R.string.ingresar_producto_sucursal))
            }
            Button(onClick = { controladorNavegacion.navigate("buscar_stock") }) {
                Text(text = stringResource(id = R.string.buscar_stock_repuestos))
            }
        }

        Button(
            onClick = {
                val currentLang = context.resources.configuration.locales[0].language
                val newLang = if (currentLang == "es") "en" else "es"
                AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(newLang))
            },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
        ) {
            val currentLang = context.resources.configuration.locales[0].language
            Text(text = if (currentLang == "es") "EN" else "ES")
        }
    }
}
