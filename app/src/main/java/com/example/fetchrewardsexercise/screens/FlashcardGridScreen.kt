package com.example.fetchrewardsexercise.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fetchrewardsexercise.Item
import com.example.fetchrewardsexercise.ui.theme.md_theme_light_onSurface
import com.example.fetchrewardsexercise.ui.theme.md_theme_light_primary
import com.example.fetchrewardsexercise.ui.theme.md_theme_light_secondary
import com.example.fetchrewardsexercise.ui.theme.md_theme_light_surface

@Composable
fun FlashcardGridScreen(items: List<Item>, navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    val groupedItems = items.groupBy { it.listId }.toSortedMap()
    val filteredLists = groupedItems.keys
        .filter { listId -> "List $listId".contains(searchQuery, ignoreCase = true) }
        .toList()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Search List") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = md_theme_light_primary,
                unfocusedBorderColor = md_theme_light_secondary
            )
        )

        if (filteredLists.isEmpty() && searchQuery.isNotEmpty()) {
            Text(
                text = "No items found!",
                modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
                textAlign = TextAlign.Center,
                color = md_theme_light_onSurface
            )
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(filteredLists) { listId ->
                    Card(
                        modifier = Modifier
                            .aspectRatio(1f)
                            .clickable {
                                navController.navigate("listDetail/$listId")
                            },
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        colors = CardDefaults.cardColors(containerColor = md_theme_light_surface),
                        border = BorderStroke(1.dp, md_theme_light_primary)
                    ) {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text(
                                text = "List $listId",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = md_theme_light_primary
                            )
                        }
                    }
                }
            }
        }
    }
}