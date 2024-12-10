package com.zhadko.topredditpostsviewer.ui.authScreen

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.zhadko.topredditpostsviewer.base.BaseFragment
import com.zhadko.topredditpostsviewer.databinding.AuthFragmentBinding
import com.zhadko.topredditpostsviewer.utils.extensions.collectAsState
import org.koin.androidx.viewmodel.ext.android.viewModel

class AuthFragment : BaseFragment<AuthFragmentBinding>(AuthFragmentBinding::inflate) {

    private val authViewModel by viewModel<AuthViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupCollectors()
    }

    override fun onResume() {
        super.onResume()
        authViewModel.getAuthData(requireActivity().intent)
    }

    private fun setupView() {
        binding.login.setOnClickListener {
            authViewModel.login()
        }
    }

    private fun setupCollectors() {
        collectAsState(authViewModel.state, ::handleState)
    }

    private fun handleState(state: AuthState) {
        if (state.isLogged) navigateToDetailedScreen()
        binding.progressBar.isVisible = state.isLoading
        binding.login.isEnabled = !state.isLoading
    }

    private fun navigateToDetailedScreen() {
        findNavController().navigate(AuthFragmentDirections.actionAuthFragmentToTopPostsListFragment())
    }
}