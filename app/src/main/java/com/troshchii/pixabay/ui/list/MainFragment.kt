package com.troshchii.pixabay.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.troshchii.pixabay.databinding.FragmentMainBinding
import com.troshchii.pixabay.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel by viewModels<MainViewModel>()
    private var binding: FragmentMainBinding by viewBinding()
    private val adapter = HitsAdapter(::openDetailsScreen)

    private fun openDetailsScreen(url: String) {
        val action = MainFragmentDirections.toDetailsFragment(url)
        findNavController().navigate(action)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.list.adapter = adapter.withLoadStateFooter(LoadingAdapter())

        lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest { loadState ->
                val isLoading = loadState.refresh is LoadState.Loading
                binding.progressBar.isVisible = isLoading
                binding.list.isVisible = !isLoading
            }
        }

        lifecycleScope.launch {
            viewModel.hits
                .flowWithLifecycle(lifecycle)
                .collect {
                    adapter.submitData(it)
                }
        }
    }
}


