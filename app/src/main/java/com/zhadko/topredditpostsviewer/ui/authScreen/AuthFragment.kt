package com.zhadko.topredditpostsviewer.ui.authScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zhadko.topredditpostsviewer.R
import com.zhadko.topredditpostsviewer.base.BaseFragment
import com.zhadko.topredditpostsviewer.databinding.AuthFragmentBinding
import com.zhadko.topredditpostsviewer.ui.topPostsListScreen.TopPostsListFragment
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
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.my_fragment_container, TopPostsListFragment())
                    .commit()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        authViewModel.getAuthData(requireActivity().intent)
    }
}