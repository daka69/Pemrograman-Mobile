package com.example.scrollablelist_xml.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.example.scrollablelist_xml.data.SeriesData
import com.example.scrollablelist_xml.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val seriesId = arguments?.getString("seriesId")
        val series = SeriesData.seriesList.find { it.id == seriesId }

        series?.let {
            binding.apply {
                ivPoster.load(it.imageUrl)
                tvTitle.text = it.title
                tvYear.text = it.year
                tvGenre.text = it.genre
                tvCreator.text = it.creator
                tvCast.text = it.cast
                tvLabel.text = it.label
                tvPlot.text = it.plot
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}