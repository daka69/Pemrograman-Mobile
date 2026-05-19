package com.example.scrollablelist_xml.viewmodel

import androidx.lifecycle.ViewModel
import com.example.scrollablelist_xml.data.SeriesData
import com.example.scrollablelist_xml.model.Series
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber

class SeriesViewModel(private val infoParameter: String) : ViewModel() {

    private val _seriesList = MutableStateFlow<List<Series>>(emptyList())
    val seriesList: StateFlow<List<Series>> = _seriesList.asStateFlow()

    private val _navigateToDetail = MutableStateFlow<String?>(null)
    val navigateToDetail: StateFlow<String?> = _navigateToDetail.asStateFlow()

    private val _openImdb = MutableStateFlow<String?>(null)
    val openImdb: StateFlow<String?> = _openImdb.asStateFlow()

    init {
        Timber.d("ViewModel diinisialisasi dengan parameter: $infoParameter")
        loadData()
    }

    private fun loadData() {
        val data = SeriesData.seriesList
        _seriesList.value = data
        Timber.d("Data item berhasil masuk ke dalam list. Total: ${data.size} item")
    }

    fun onDetailClicked(series: Series) {
        Timber.d("Tombol Detail ditekan")
        Timber.d("Data list yang dipilih untuk Detail: $series")
        _navigateToDetail.value = series.id
    }

    fun onDetailNavigated() {
        _navigateToDetail.value = null
    }

    fun onImdbClicked(series: Series) {
        Timber.d("Tombol Explicit Intent (IMDB) ditekan")
        Timber.d("URL Tujuan: ${series.imdbUrl}")
        _openImdb.value = series.imdbUrl
    }

    fun onImdbOpened() {
        _openImdb.value = null
    }
}