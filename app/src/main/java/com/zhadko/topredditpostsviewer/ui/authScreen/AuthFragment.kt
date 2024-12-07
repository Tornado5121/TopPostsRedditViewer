package com.zhadko.topredditpostsviewer.ui.authScreen

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.zhadko.topredditpostsviewer.base.BaseFragment
import com.zhadko.topredditpostsviewer.databinding.AuthFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class AuthFragment : BaseFragment<AuthFragmentBinding>(AuthFragmentBinding::inflate) {

    private val authViewModel by viewModel<AuthViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.login.setOnClickListener {
            authViewModel.login()
        }

        authViewModel.authTokenLiveData.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                findNavController().navigate(AuthFragmentDirections.actionAuthFragmentToTopPostsListFragment())
            }
        }
    }

    override fun onResume() {
        super.onResume()
        authViewModel.getAuthData(requireActivity().intent)
    }
}