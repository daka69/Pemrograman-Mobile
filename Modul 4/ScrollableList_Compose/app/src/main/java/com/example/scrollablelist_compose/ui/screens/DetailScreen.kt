package com.example.scrollablelist_compose.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.scrollablelist_compose.data.SeriesData

@Composable
fun DetailScreen(seriesId: String?) {
    val series = SeriesData.seriesList.find { it.id == seriesId }

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        if (series != null) {
            item {
                AsyncImage(
                    model = series.imageUrl,
                    contentDescription = series.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(550.dp)
                        .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
                )
            }

            item {
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Text(
                            text = series.title,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.weight(1f),
                            lineHeight = 34.sp
                        )
                        Text(
                            text = series.year,
                            fontSize = 22.sp,
                            modifier = Modifier.padding(start = 12.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = series.label,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = series.plot,
                        fontSize = 16.sp,
                        lineHeight = 24.sp
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(text = "Genre", fontWeight = FontWeight.Bold)
                    Text(text = series.genre)

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(text = "Creator", fontWeight = FontWeight.Bold)
                    Text(text = series.creator)

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(text = "Cast", fontWeight = FontWeight.Bold)
                    Text(text = series.cast)

                    Spacer(modifier = Modifier.height(50.dp))
                }
            }
        }
    }
}