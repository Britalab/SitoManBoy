package com.example.sitomanboy.ui.Screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sitomanboy.viewmodel.SitoManBoyViewModel

class CrearRepuestosActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: SitoManBoyViewModel = viewModel()
            CrearRepuestoScreen(viewModel) {
                finish() // vuelve a MainActivity
            }
        }
    }
}
