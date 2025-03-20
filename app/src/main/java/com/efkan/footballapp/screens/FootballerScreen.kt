package com.efkan.footballapp.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.efkan.footballapp.model.Footballer
import com.efkan.footballapp.viewmodal.FootballerViewModal

@Composable
fun HomeScreen(navController: NavHostController, viewModel: FootballerViewModal) {
    // Arama butonuna tıklama işlevi
    Column(modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.primaryContainer)) {
        FootballerSearch { searchQuery ->
            viewModel.getFootballer(searchQuery)
        }
        Spacer(modifier = Modifier.height(8.dp))
        FootballerList(footballerList = viewModel.footballerList.value ?: emptyList(), navController = navController)
    }
}

@Composable
fun FootballerList(footballerList: List<Footballer>, navController: NavHostController) {
    LazyColumn(modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.primaryContainer)) {
        items(footballerList) { footballer ->
            FootballerRow(footballer = footballer, navController = navController)
        }
    }
}

@Composable
fun FootballerRow(footballer: Footballer, navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(BorderStroke(2.dp, androidx.compose.ui.graphics.Color.Black))
            .background(color = MaterialTheme.colorScheme.primaryContainer)
            .padding(10.dp)
            .clickable {
                navController.navigate("footballer_detail/${footballer.strPlayer.replace(" ", "_")}")
            }
    ) {
        Text(
            text = footballer.strPlayer,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(2.dp),
            fontWeight = FontWeight.Normal
        )
    }
}

@Composable
fun FootballerSearch(onSearch: (String) -> Unit) {
    var searchText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            label = { Text("Futbolcu Adı") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { onSearch(searchText) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ara")
        }
    }
}
