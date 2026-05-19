package com.example.scrollablelist_compose.model

data class Series(
    val id: String,
    val title: String,
    val year: String,
    val label: String,
    val plot: String,
    val imdbUrl: String,
    val imageUrl: String,
    val rating: String,
    val genre: String,
    val creator: String,
    val cast: String
)