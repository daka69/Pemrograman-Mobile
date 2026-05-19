package com.example.scrollablelist_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.scrollablelist_compose.ui.screens.DetailScreen
import com.example.scrollablelist_compose.ui.screens.ListScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "list_screen") {
                    composable("list_screen") {
                        ListScreen(
                            onNavigateToDetail = { seriesId ->
                                navController.navigate("detail_screen/$seriesId")
                            }
                        )
                    }
                    composable("detail_screen/{seriesId}") { backStackEntry ->
                        val seriesId = backStackEntry.arguments?.getString("seriesId")
                        DetailScreen(seriesId = seriesId)
                    }
                }
            }
        }
    }
}