package com.ripalnakiya.juicetracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.ripalnakiya.juicetracker.databinding.FragmentTrackerBinding
import com.ripalnakiya.juicetracker.ui.AppViewModelProvider
import com.ripalnakiya.juicetracker.ui.JuiceListAdapter
import com.ripalnakiya.juicetracker.ui.TrackerViewModel
import kotlinx.coroutines.launch

class TrackerFragment : Fragment() {

    private val viewModel by viewModels<TrackerViewModel> { AppViewModelProvider.Factory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentTrackerBinding.inflate(inflater, container, false).root
    }

    private val adapter = JuiceListAdapter(
        onEdit = { drink ->
            findNavController().navigate(
                TrackerFragmentDirections.actionTrackerFragmentToEntryDialogFragment(drink.id)
            )
        },
        onDelete = { drink ->
            viewModel.deleteJuice(drink)
        }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentTrackerBinding.bind(view)
        binding.recyclerView.adapter = adapter

        binding.fab.setOnClickListener { fabView ->
            fabView.findNavController().navigate(
                TrackerFragmentDirections.actionTrackerFragmentToEntryDialogFragment()
            )
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.juicesStream.collect {
                    adapter.submitList(it)
                }
            }
        }
    }
}