package com.example.fetchrewardsexercise.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fetchrewardsexercise.Item
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.ui.text.font.FontWeight
import com.example.fetchrewardsexercise.ui.theme.md_theme_light_primary
import com.example.fetchrewardsexercise.ui.theme.md_theme_light_surface
import com.example.fetchrewardsexercise.ui.theme.md_theme_light_onSurface
import com.example.fetchrewardsexercise.ui.theme.md_theme_light_secondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListDetailScreen(listId: Int, items: List<Item>, navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    val listItems = items.filter { it.listId == listId }.sortedBy { it.name }
    val filteredItems = listItems.filter { it.name.contains(searchQuery, ignoreCase = true) }
    var selectedItemId by remember { mutableStateOf<Int?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("List $listId Items", color = md_theme_light_onSurface) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = md_theme_light_primary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = md_theme_light_surface
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Search Item") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = md_theme_light_primary,
                    unfocusedBorderColor = md_theme_light_secondary
                )
            )

            if (filteredItems.isEmpty() && searchQuery.isNotEmpty()) {
                Text(
                    text = "No items found!",
                    modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
                    textAlign = TextAlign.Center,
                    color = md_theme_light_onSurface
                )
            } else {
                Text(
                    color = md_theme_light_primary,
                    text = "List $listId",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(filteredItems) { item ->
                        ItemCard(
                            item = item,
                            isSelected = selectedItemId == item.id,
                            onClick = { selectedItemId = if (selectedItemId == item.id) null else item.id }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ItemCard(item: Item, isSelected: Boolean, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clickable(onClick = onClick)
            .padding(4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        colors = CardDefaults.cardColors(
            containerColor = md_theme_light_surface
        ),
        border = if (isSelected) BorderStroke(2.dp, md_theme_light_primary) else BorderStroke(1.dp, md_theme_light_onSurface),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = item.name,
                fontSize = 16.sp,
                color = md_theme_light_onSurface,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)  // Take available space, ensuring text fits
                    .padding(horizontal = 8.dp)
            )
            if (isSelected) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Selected",
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(24.dp),
                    tint = md_theme_light_primary
                )
            }
        }
    }
}