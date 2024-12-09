package com.zhadko.topredditpostsviewer.ui.detailedPostScreen

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.zhadko.topredditpostsviewer.base.BaseFragment
import com.zhadko.topredditpostsviewer.databinding.DetailedPostFragmentBinding
import com.zhadko.topredditpostsviewer.utils.MyPermissionsHelper.REQUIRED_PERMISSIONS
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class DetailedPostFragment :
    BaseFragment<DetailedPostFragmentBinding>(DetailedPostFragmentBinding::inflate) {

    private val navArgs by navArgs<DetailedPostFragmentArgs>()

    private val detailedPostViewModel: DetailedPostViewModel by viewModel {
        parametersOf(navArgs.postId)
    }

    private val permissionRequestLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            detailedPostViewModel.saveImageToGallery(binding.bigSizeTopPostPicture)
        }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            detailedPostViewModel.state.collect {
                Glide.with(requireContext()).load(it.postModel?.bigSizePictureUrl)
                    .into(binding.bigSizeTopPostPicture)
            }
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
                permissionRequestLauncher.launch(REQUIRED_PERMISSIONS)
            }
        }
    }
}