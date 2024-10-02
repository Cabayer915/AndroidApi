package com.example.filmesapi

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FilmesViewModel : ViewModel() {

    private val filmes = mutableStateListOf<Filme>()
    private val filmesApi:FilmesApi

    init {
        filmesApi = RetrofitService.getApiFilmes()
        iniciarLista()
    }

    private fun iniciarLista() {
        GlobalScope.launch {
            try {
                val resposta = filmesApi.getFilmes()
                if (resposta.isSuccessful) {
                    Log.i("api", "filmes da API: ${resposta.body()}")
                    filmes.clear()
                    filmes.addAll(resposta.body() ?: emptyList())
                } else {
                    Log.e("api", "Erro ao buscar filmes: ${resposta.errorBody()?.string()}")
                }
            } catch (exception: Exception) {
                Log.e("api", "Erro ao buscar filmes", exception)
            }
        }
    }

    fun getFilmes() = filmes.toList()

    fun addFilmes(novoFilme : Filme) {
        GlobalScope.launch {
            try {
                var resposta = filmesApi.postFilme(novoFilme)
                if (resposta.isSuccessful) {
                    filmes.add(resposta.body()!!)
                } else {
                    Log.e("api", "Erro ao adicionar filme: ${resposta.errorBody()}")
                }
            } catch (exception: Exception) {
                Log.e("api", "Erro ao adicionar filme", exception)
            }
        }
    }

}