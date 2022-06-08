package com.zhadko.topredditpostsviewer.ui.detailedPostScreen

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.zhadko.topredditpostsviewer.databinding.DetailedPostFragmentBinding
import com.zhadko.topredditpostsviewer.helpers.MyPermissionsHelper
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class DetailedPostFragment : Fragment() {

    private lateinit var binding: DetailedPostFragmentBinding

    private val detailedPostViewModel: DetailedPostViewModel by viewModel {
        parametersOf(requireArguments().getString("top_post_id").toString())
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        detailedPostViewModel.saveImageToGallery(binding.bigSizeTopPostPicture)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailedPostFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailedPostViewModel.topPostLiveData.observe(viewLifecycleOwner) {
            Glide.with(requireContext()).load(it.bigSizePictureUrl)
                .into(binding.bigSizeTopPostPicture)
        }

        detailedPostViewModel.errorMessageLiveData.observe(viewLifecycleOwner) {
            if (it.equals("Error"))
                Toast.makeText(
                    requireContext(),
                    "Something goes wrong, try again later, please",
                    Toast.LENGTH_LONG
                ).show()
        }
        detailedPostViewModel.getTopPostById()

        binding.saveToGalleryButton.setOnClickListener {
            if (detailedPostViewModel.isStoragePermissionGranted()) {
                detailedPostViewModel.saveImageToGallery(binding.bigSizeTopPostPicture)
            } else {
                requestMyStoragePermissions()
            }
        }
    }

    private fun requestMyStoragePermissions() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            MyPermissionsHelper.REQUIRED_PERMISSIONS,
            MyPermissionsHelper.REQUEST_CODE_PERMISSIONS
        )
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