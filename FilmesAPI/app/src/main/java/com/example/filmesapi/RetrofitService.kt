package com.example.filmesapi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    val BASE_URL_FILMES = "https://5f861cfdc8a16a0016e6aacd.mockapi.io/sptech-api/"

    fun getApiFilmes(): FilmesApi {
        val cliente =
            Retrofit.Builder()
                .baseUrl(BASE_URL_FILMES)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(FilmesApi::class.java)
        return cliente
    }
}