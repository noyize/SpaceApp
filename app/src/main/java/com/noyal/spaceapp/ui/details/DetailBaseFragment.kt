package com.noyal.spaceapp.ui.details

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.noyal.spaceapp.R
import com.noyal.spaceapp.databinding.FragmentDetailBaseBinding
import com.noyal.spaceapp.ui.main.MainViewModel
import com.noyal.spaceapp.ui.main.NewsAdapter
import com.noyal.spaceapp.util.GridSpacingItemDecoration
import com.noyal.spaceapp.util.Resource


class DetailBaseFragment : Fragment(R.layout.fragment_detail_base) {

    private val viewModel: MainViewModel by activityViewModels()
    private val args: DetailBaseFragmentArgs by navArgs()
    private lateinit var binding: FragmentDetailBaseBinding
    private lateinit var detailAdapter: DetailAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDetailBaseBinding.bind(view)

        setUpRecyclerView()
        gotoDetails(args.position)
    }

    private fun setUpRecyclerView() {
        detailAdapter = DetailAdapter()
        binding.apply {

            val snapHelper = LinearSnapHelper()
            snapHelper.attachToRecyclerView(recyclerView)
            recyclerView.apply {
                setHasFixedSize(true)
                fling(0,0)
                adapter = detailAdapter
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            }
        }
    }

    private fun gotoDetails(position: Int) {
        viewModel.spaceNews.asLiveData().observe(viewLifecycleOwner) {
            when (it) {

                is Resource.Success -> {
                    detailAdapter.submitList(it.value)
                    binding.recyclerView.scrollToPosition(position)
                }
                else -> {
                    Toast.makeText(requireContext(), "Error opening details", Toast.LENGTH_LONG)
                        .show()
                    findNavController().navigateUp()
                }
            }
        }
    }
}
