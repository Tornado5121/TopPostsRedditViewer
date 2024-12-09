package com.zhadko.topredditpostsviewer.ui.authScreen

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.zhadko.topredditpostsviewer.base.BaseFragment
import com.zhadko.topredditpostsviewer.databinding.AuthFragmentBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class AuthFragment : BaseFragment<AuthFragmentBinding>(AuthFragmentBinding::inflate) {

    private val authViewModel by viewModel<AuthViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.login.setOnClickListener {
            authViewModel.login()
        }

        lifecycleScope.launch {
            authViewModel.state.collect {
                if (it.isLogged) {
                    findNavController().navigate(AuthFragmentDirections.actionAuthFragmentToTopPostsListFragment())
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        authViewModel.getAuthData(requireActivity().intent)
    }
}