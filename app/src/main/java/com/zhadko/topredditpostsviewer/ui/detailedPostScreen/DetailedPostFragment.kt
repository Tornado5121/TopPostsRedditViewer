package com.zhadko.topredditpostsviewer.ui.detailedPostScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.zhadko.topredditpostsviewer.databinding.DetailedPostFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetailedPostFragment : Fragment() {

    private lateinit var binding: DetailedPostFragmentBinding

    private val detailedPostViewModel: DetailedPostViewModel by viewModel {
        parametersOf(requireArguments().getString("top_post_id").toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailedPostFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailedPostViewModel.topPostLiveData.observe(viewLifecycleOwner) {
            Glide.with(requireContext()).load(it.bigSizePictureUrl)
                .into(binding.bigSizeTopPostPicture)
        }
        detailedPostViewModel.getTopPostById()
    }

    companion object {

        private const val TOP_POST_ID = "top_post_id"
        fun getInstance(id: String): DetailedPostFragment {
            return DetailedPostFragment().apply {
                arguments = Bundle().apply {
                    putString(TOP_POST_ID, id)
                }
            }
        }

    }

}