package com.zhadko.topredditpostsviewer.ui.authScreen

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhadko.topredditpostsviewer.domain.repositories.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val mAuthTokenLiveData = MutableLiveData<String>()
    val authTokenLiveData = mAuthTokenLiveData

    init {
        viewModelScope.launch(Dispatchers.IO) {
            authRepository.authFlow.collectLatest {
                mAuthTokenLiveData.postValue(it)
            }
        }
    }

    fun login() {
        viewModelScope.launch(Dispatchers.IO) {
            authRepository.login()
        }
    }

    fun getAuthData(intent: Intent) {
        authRepository.getData(intent)
    }

}