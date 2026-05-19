package com.example.scrollablelist_xml.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.scrollablelist_xml.R
import com.example.scrollablelist_xml.adapter.SeriesHorizontalAdapter
import com.example.scrollablelist_xml.adapter.SeriesVerticalAdapter
import com.example.scrollablelist_xml.data.SeriesData
import com.example.scrollablelist_xml.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val reversedList = SeriesData.seriesList.reversed()

        binding.rvHorizontal.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvHorizontal.adapter = SeriesHorizontalAdapter(reversedList) { seriesId ->
            val bundle = Bundle().apply { putString("seriesId", seriesId) }
            findNavController().navigate(R.id.action_listFragment_to_detailFragment, bundle)
        }

        binding.rvVertical.layoutManager = LinearLayoutManager(requireContext())
        binding.rvVertical.adapter = SeriesVerticalAdapter(
            list = SeriesData.seriesList,
            onDetailClick = { seriesId ->
                val bundle = Bundle().apply { putString("seriesId", seriesId) }
                findNavController().navigate(R.id.action_listFragment_to_detailFragment, bundle)
            },
            onImdbClick = { url ->
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}