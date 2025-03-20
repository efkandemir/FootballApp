package com.efkan.footballapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.efkan.footballapp.model.Footballer
import com.efkan.footballapp.viewmodal.FootballerViewModal

@Composable
fun FootballerDetailScreen(playerName: String, viewModel: FootballerViewModal) {
    LaunchedEffect(playerName) {
        viewModel.getFootballer(playerName) // API'den futbolcu bilgilerini al
    }
    //playerName değiştiğinde, LaunchedEffect yeniden çalıştırılır ve içindeki kod yeniden çalıştırılır.

    val footballer = viewModel.getFootballerByName(playerName) // Tek bir futbolcuyu al

    // Eğer futbolcu bilgisi gelmediyse, yükleniyor mesajı göster
    if (footballer == null) {
        Text("Futbolcu bilgisi yükleniyor...")
        return
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colorScheme.primaryContainer),
        contentAlignment = Alignment.Center
    )
    {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Text(
                text = footballer.strPlayer,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Image(
                painter = rememberAsyncImagePainter(footballer.strThumb),
                contentDescription = "Player Image",
                modifier = Modifier.size(250.dp).padding(bottom = 8.dp),
                alignment = Alignment.Center
            )

            Text(
                text = "Pozisyon: ${footballer.strPosition}",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "Takım: ${footballer.strTeam}",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )


            Text(
                text = "Milliyet: ${footballer.strNationality}",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }


}

