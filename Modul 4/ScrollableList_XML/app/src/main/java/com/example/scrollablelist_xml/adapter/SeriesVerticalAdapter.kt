package com.example.scrollablelist_xml.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.scrollablelist_xml.databinding.ItemSeriesVerticalBinding
import com.example.scrollablelist_xml.model.Series

class SeriesVerticalAdapter(
    var list: List<Series> = emptyList(),
    private val onDetailClick: (Series) -> Unit,
    private val onImdbClick: (Series) -> Unit
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
            tvPlot.text = series.plot
            ivPoster.load(series.imageUrl) {
                crossfade(true)
                transformations(RoundedCornersTransformation(8f))
            }
            btnDetail.setOnClickListener { onDetailClick(series) }
            btnImdb.setOnClickListener { onImdbClick(series) }
        }
    }

    override fun getItemCount(): Int = list.size
}