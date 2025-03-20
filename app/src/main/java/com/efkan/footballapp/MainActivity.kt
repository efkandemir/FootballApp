package com.efkan.footballapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.efkan.footballapp.screens.FootballerDetailScreen
import com.efkan.footballapp.screens.HomeScreen
import com.efkan.footballapp.ui.theme.FootballAppTheme
import com.efkan.footballapp.viewmodal.FootballerViewModal

class MainActivity : ComponentActivity() {
    private val viewModel:FootballerViewModal by viewModels<FootballerViewModal>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            FootballAppTheme {
                FootballAppTheme {
                    val navController = rememberNavController() // NavController

                    // Scaffold içinde NavHost ile tüm ekranları render ettik.
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        Box(modifier = Modifier.padding(innerPadding)) {
                            // Navigation graph burada tanımlıyruz.
                            NavHost(navController = navController, startDestination = "home") {
                                composable("home") {
                                    HomeScreen(navController = navController, viewModel = viewModel)
                                }
                                composable("footballer_detail/{playerName}") { backStackEntry ->
                                    FootballerDetailScreen(
                                        playerName = backStackEntry.arguments?.getString(
                                            "playerName"
                                        ) ?: "",
                                        viewModel = viewModel
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

