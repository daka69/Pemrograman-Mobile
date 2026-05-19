package com.example.scrollablelist_xml.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.scrollablelist_xml.databinding.ItemSeriesHorizontalBinding
import com.example.scrollablelist_xml.model.Series

class SeriesHorizontalAdapter(
    var list: List<Series> = emptyList(),
    private val onDetailClick: (Series) -> Unit
) : RecyclerView.Adapter<SeriesHorizontalAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemSeriesHorizontalBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSeriesHorizontalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val series = list[position]
        holder.binding.apply {
            tvTitle.text = series.title
            ivPoster.load(series.imageUrl) {
                crossfade(true)
                transformations(RoundedCornersTransformation(12f))
            }
            root.setOnClickListener { onDetailClick(series) }
        }
    }

    override fun getItemCount(): Int = list.size
}