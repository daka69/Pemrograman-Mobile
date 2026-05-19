package com.example.scrollablelist_compose.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.scrollablelist_compose.data.SeriesData
import com.example.scrollablelist_compose.ui.components.SeriesHorizontalItem
import com.example.scrollablelist_compose.ui.components.SeriesItem

@Composable
fun ListScreen(
    onNavigateToDetail: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        Text("Sedang Populer", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(12.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(SeriesData.seriesList.reversed()) { series ->
                SeriesHorizontalItem(
                    series = series,
                    onDetailClick = onNavigateToDetail
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("Rekomendasi Lainnya", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(SeriesData.seriesList) { series ->
                SeriesItem(
                    series = series,
                    onDetailClick = onNavigateToDetail
                )
            }
        }
    }
}