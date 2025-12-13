package com.example.pruebados.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Repuesto(
    val id: String,
    val serie: String,
    val descripcion: String,
    var stock: Int
) : Parcelable
