package com.example.sitomanboy.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sitomanboy.ui.Screens.IngresarProductoScreen
import com.example.sitomanboy.viewmodel.SitoManBoyViewModel

class IngresarProductoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: SitoManBoyViewModel = viewModel()
            IngresarProductoScreen(viewModel) {
                finish()
            }
        }
    }
}
