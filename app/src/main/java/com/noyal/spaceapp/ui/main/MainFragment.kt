package com.noyal.spaceapp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.noyal.spaceapp.R
import com.noyal.spaceapp.databinding.FragmentMainBinding
import com.noyal.spaceapp.util.GridSpacingItemDecoration
import com.noyal.spaceapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main), NewsAdapter.OnItemClickListener {

    private lateinit var binding: FragmentMainBinding
    private lateinit var newsAdapter: NewsAdapter
    private val viewModel: MainViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        setUpRecyclerView()
        observeSpaceNews()

    }

    private fun setUpRecyclerView() {
        newsAdapter = NewsAdapter(this)
        binding.apply {
            recyclerView.apply {
                setHasFixedSize(true)
                adapter = newsAdapter
                layoutManager = GridLayoutManager(requireContext(), 2)
                addItemDecoration(GridSpacingItemDecoration(2,32,true))
            }
        }
    }

    private fun observeSpaceNews() {
        viewModel.spacePicture.asLiveData().observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> binding.progressBar.isVisible = true
                is Resource.Success -> {
                    binding.progressBar.isVisible = false
                    newsAdapter.submitList(it.value)
                }
                is Resource.Error -> {
                    binding.progressBar.isVisible = false
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_LONG).show()
                }
                else -> Unit
            }
        }
    }

    override fun onItemClick(position:Int) {
        findNavController().navigate(MainFragmentDirections.actionMainFragmentToDetailFragment(position))
    }
}