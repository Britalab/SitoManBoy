package com.example.sitomanboy.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sitomanboy.ui.Screens.BuscarStockScreen
import com.example.sitomanboy.viewmodel.SitoManBoyViewModel

class BuscarStockActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: SitoManBoyViewModel = viewModel()
            BuscarStockScreen(viewModel)
        }
    }
}
