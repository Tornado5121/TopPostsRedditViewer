package com.zhadko.topredditpostsviewer.ui.topPostsListScreen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.zhadko.topredditpostsviewer.base.BaseFragment
import com.zhadko.topredditpostsviewer.databinding.TopPostsListFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class TopPostsListFragment :
    BaseFragment<TopPostsListFragmentBinding>(TopPostsListFragmentBinding::inflate) {

    private val topPostsViewModel by viewModel<TopPostsListViewModel>()

    private val topPostsListAdapter by lazy {
        TopPostsListAdapter(requireContext(), {
            if (it.bigSizePictureUrl.contains("https://", true)) {
                findNavController().navigate(
                    TopPostsListFragmentDirections.actionTopPostsListFragmentToDetailedPostFragment(
                        it.id
                    )
                )
            } else {
                Toast.makeText(
                    requireContext(),
                    "Sorry, there is no opportunity to review this post",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }, {
            binding.progressBar.isVisible = true
            topPostsViewModel.getTopPostsNewPage()
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            topPostsList.adapter = topPostsListAdapter
            topPostsList.layoutManager = LinearLayoutManager(requireContext())
        }

        topPostsViewModel.topPostsLiveData.observe(viewLifecycleOwner) {
            topPostsListAdapter.submitList(it)
            binding.progressBar.isVisible = false
        }

        topPostsViewModel.getTopPostsNewPage()
    }

}