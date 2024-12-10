package com.zhadko.topredditpostsviewer.ui.authScreen

import android.content.Intent
import androidx.lifecycle.viewModelScope
import com.zhadko.topredditpostsviewer.base.BaseViewModel
import com.zhadko.topredditpostsviewer.domain.repositories.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authRepository: AuthRepository,
) : BaseViewModel<AuthState>(
    AuthState(
        isLoading = true,
        isLogged = false
    )
) {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            authRepository.authFlow.collectLatest { authData ->
                updateState(state.value.copy(isLoading = false, isLogged = authData.isNotBlank()))
            }
        }
    }

    fun login() {
        viewModelScope.launch(Dispatchers.IO) {
            authRepository.login()
        }
    }

    fun getAuthData(intent: Intent) {
        viewModelScope.launch {
            authRepository.getData(intent)
        }
    }
}