package com.example.filmesapi

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface FilmesApi {

    @GET("/filmes")
    suspend fun getFilmes(): Response<List<Filme>>

    @POST("/filmes")
    suspend fun postFilme(@Body filme: Filme): Response<Filme>
}