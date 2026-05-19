package com.example.scrollablelist_xml.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.scrollablelist_xml.databinding.ItemSeriesHorizontalBinding
import com.example.scrollablelist_xml.model.Series

class SeriesHorizontalAdapter(
    private val list: List<Series>,
    private val onItemClick: (String) -> Unit
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
            ivPoster.load(series.imageUrl)
            root.setOnClickListener { onItemClick(series.id) }
        }
    }

    override fun getItemCount() = list.size
}