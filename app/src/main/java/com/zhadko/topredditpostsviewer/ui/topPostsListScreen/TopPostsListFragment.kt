package com.zhadko.topredditpostsviewer.ui.topPostsListScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.zhadko.topredditpostsviewer.R
import com.zhadko.topredditpostsviewer.databinding.TopPostsListFragmentBinding
import com.zhadko.topredditpostsviewer.ui.detailedPostScreen.DetailedPostFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class TopPostsListFragment : Fragment() {

    private lateinit var binding: TopPostsListFragmentBinding

    private val topPostsViewModel by viewModel<TopPostsListViewModel>()

    private val topPostsListAdapter by lazy {
        TopPostsListAdapter(requireContext(), {
            requireActivity().supportFragmentManager.beginTransaction()
                .addToBackStack("")
                .replace(R.id.my_fragment_container, DetailedPostFragment.getInstance(it.id))
                .commit()
        }, {
            topPostsViewModel.getTopPostsNewPage()
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TopPostsListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            topPostsList.adapter = topPostsListAdapter
            topPostsList.layoutManager = LinearLayoutManager(requireContext())
        }

        topPostsViewModel.topPostsLiveData.observe(viewLifecycleOwner) {
            topPostsListAdapter.submitList(it)
        }

        topPostsViewModel.getTopPostsNewPage()
    }

}