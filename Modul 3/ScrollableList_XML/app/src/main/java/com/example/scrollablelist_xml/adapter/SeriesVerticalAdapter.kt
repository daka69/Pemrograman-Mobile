package com.example.scrollablelist_xml.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.scrollablelist_xml.databinding.ItemSeriesVerticalBinding
import com.example.scrollablelist_xml.model.Series

class SeriesVerticalAdapter(
    private val list: List<Series>,
    private val onDetailClick: (String) -> Unit,
    private val onImdbClick: (String) -> Unit
) : RecyclerView.Adapter<SeriesVerticalAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemSeriesVerticalBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSeriesVerticalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val series = list[position]
        holder.binding.apply {
            tvTitle.text = series.title
            tvYear.text = series.year
            tvLabel.text = series.label
            tvPlot.text = series.plot
            ivPoster.load(series.imageUrl)

            btnDetail.setOnClickListener { onDetailClick(series.id) }
            btnImdb.setOnClickListener { onImdbClick(series.imdbUrl) }
        }
    }

    override fun getItemCount() = list.size
}