package com.example.scrollablelist_xml.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.scrollablelist_xml.R
import com.example.scrollablelist_xml.adapter.SeriesHorizontalAdapter
import com.example.scrollablelist_xml.adapter.SeriesVerticalAdapter
import com.example.scrollablelist_xml.databinding.FragmentListBinding
import com.example.scrollablelist_xml.viewmodel.SeriesViewModel
import com.example.scrollablelist_xml.viewmodel.SeriesViewModelFactory
import kotlinx.coroutines.launch

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SeriesViewModel by viewModels {
        SeriesViewModelFactory("Modul 4 XML: Parameter List Film")
    }

    private lateinit var horizontalAdapter: SeriesHorizontalAdapter
    private lateinit var verticalAdapter: SeriesVerticalAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerViews()
        observeViewModel()
    }

    private fun setupRecyclerViews() {
        horizontalAdapter = SeriesHorizontalAdapter(emptyList()) { series ->
            viewModel.onDetailClicked(series)
        }
        binding.rvHorizontal.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = horizontalAdapter
        }

        verticalAdapter = SeriesVerticalAdapter(
            list = emptyList(),
            onDetailClick = { series -> viewModel.onDetailClicked(series) },
            onImdbClick = { series -> viewModel.onImdbClicked(series) }
        )
        binding.rvVertical.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = verticalAdapter
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.seriesList.collect { list ->
                        horizontalAdapter.list = list.reversed()
                        horizontalAdapter.notifyDataSetChanged()

                        verticalAdapter.list = list
                        verticalAdapter.notifyDataSetChanged()
                    }
                }

                launch {
                    viewModel.navigateToDetail.collect { seriesId ->
                        seriesId?.let {
                            val bundle = Bundle().apply { putString("seriesId", it) }
                            findNavController().navigate(R.id.action_listFragment_to_detailFragment, bundle)
                            viewModel.onDetailNavigated()
                        }
                    }
                }

                launch {
                    viewModel.openImdb.collect { url ->
                        url?.let {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
                            startActivity(intent)
                            viewModel.onImdbOpened()
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}