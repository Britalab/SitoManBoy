package com.example.sitomanboy.models


import java.io.Serializable

data class Repuesto(
    val id: Int,
    val serie: String,
    val descripcion: String,
    var stock: Int
) : Serializable
