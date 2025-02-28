package com.example.fetchrewardsexercise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fetchrewardsexercise.screens.FlashcardGridScreen
import com.example.fetchrewardsexercise.screens.ListDetailScreen
import com.example.fetchrewardsexercise.ui.theme.FetchRewardsExerciseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FetchRewardsExerciseTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val viewModel: ItemViewModel = remember { ItemViewModel() }
                    LaunchedEffect(Unit) {
                        viewModel.fetchItems()
                    }
                    val items by viewModel.items.collectAsState()

                    NavHost(
                        navController = navController,
                        startDestination = "flashcardGrid"
                    ) {
                        composable("flashcardGrid") {
                            FlashcardGridScreen(items = items, navController = navController)
                        }
                        composable("listDetail/{listId}") { backStackEntry ->
                            val listId = backStackEntry.arguments?.getString("listId")?.toInt() ?: 1
                            ListDetailScreen(listId = listId, items = items, navController = navController)
                        }
                    }
                }
            }
        }
    }
}