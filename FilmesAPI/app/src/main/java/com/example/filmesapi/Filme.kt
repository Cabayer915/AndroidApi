package com.example.filmesapi

import com.google.gson.annotations.SerializedName

data class Filme(
    val id: Int,
    val nome: String,
    @SerializedName("custo_producao") val custoProducao: Double,
    val classico: Boolean
)
