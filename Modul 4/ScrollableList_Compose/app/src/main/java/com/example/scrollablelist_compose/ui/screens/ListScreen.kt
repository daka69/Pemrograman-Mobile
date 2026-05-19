package com.example.scrollablelist_compose.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.scrollablelist_compose.ui.components.SeriesHorizontalItem
import com.example.scrollablelist_compose.ui.components.SeriesItem
import com.example.scrollablelist_compose.viewmodel.SeriesViewModel
import com.example.scrollablelist_compose.viewmodel.SeriesViewModelFactory

@Composable
fun ListScreen(
    onNavigateToDetail: (String) -> Unit,
    viewModel: SeriesViewModel = viewModel(
        factory = SeriesViewModelFactory("Modul 4 Compose: Parameter List Film")
    )
) {
    val seriesList by viewModel.seriesList.collectAsStateWithLifecycle()
    val navigateToDetail by viewModel.navigateToDetail.collectAsStateWithLifecycle()
    val openImdb by viewModel.openImdb.collectAsStateWithLifecycle()

    val context = LocalContext.current

    LaunchedEffect(navigateToDetail) {
        navigateToDetail?.let { id ->
            onNavigateToDetail(id)
            viewModel.onDetailNavigated()
        }
    }

    LaunchedEffect(openImdb) {
        openImdb?.let { url ->
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(intent)
            viewModel.onImdbOpened()
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        Text("Sedang Populer", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(12.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(seriesList.reversed()) { series ->
                SeriesHorizontalItem(
                    series = series,
                    onDetailClick = { viewModel.onDetailClicked(series) }
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
            items(seriesList) { series ->
                SeriesItem(
                    series = series,
                    onDetailClick = { viewModel.onDetailClicked(series) },
                    onImdbClick = { viewModel.onImdbClicked(series) }
                )
            }
        }
    }
}