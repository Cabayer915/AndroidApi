package com.example.filmesapi

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.filmesapi.ui.theme.FilmesAPITheme
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FilmesAPITheme {
                Scaffold(modifier = Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(WindowInsets.safeDrawing)) { innerPadding ->
                    Tela(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun Tela(viewModel: FilmesViewModel = androidx.lifecycle.viewmodel.compose.viewModel(), modifier: Modifier = Modifier) {
    var sorteado by remember { mutableStateOf(0) }
    Column (modifier = Modifier.fillMaxSize()) {

        OutlinedTextField(
            value = "TESTE",
            onValueChange = {},
            label = {
                Text(text = "Teste")
            })

        Button(onClick = {
            sorteado = 0
            GlobalScope.launch {
                for (i in 0 .. 500_000_000_000_000){
                    Log.i("sorteio", "Sorteado: $i")
                }
            }
            sorteado = 7
        }) {
            Text(text = "Sortear", fontSize = 40.sp)
        }

        if (sorteado == 0) {
            Text(text = "Sorteando...: $sorteado", fontSize = 40.sp)
        }
        if (sorteado > 0) {
            Text(text = "Sorteado: $sorteado", fontSize = 40.sp)
        }

        LazyColumn {
            items(items = viewModel.getFilmes()) {
                filme ->
                Text("Filme: ${filme.id}. ${filme.nome} - R$${filme.custoProducao}")
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun TelaPreview() {
    FilmesAPITheme {
        Tela()
    }
}