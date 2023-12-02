package com.fgr.adik.ui.screen.dashboard.account

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fgr.adik.data.listener.ApiListener1
import com.fgr.adik.data.response.ProfileResponse
import com.fgr.adik.repository.AuthRepository
import com.fgr.adik.repository.ProfileRepository
import com.fgr.adik.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountScreenViewModel
@Inject constructor(
    private val authRepository: AuthRepository,
    private val profileRepository: ProfileRepository,
) : ViewModel() {
    private val _getProfileState = MutableStateFlow<UiState<ProfileResponse>>(UiState.Empty)
    val getProfileState
        get() = _getProfileState
    private val _uploadProfileImageState = MutableStateFlow<UiState<String>>(UiState.Empty)
    val uploadProfileImageState
        get() = _uploadProfileImageState


    val isLoginMethodGoogle = authRepository.currentUser()?.providerData?.firstOrNull {
        it.providerId == "google.com"
    }?.email != null

    fun logout(callback: () -> Unit) = authRepository.logout(callback)

    fun getProfile() {
        _getProfileState.tryEmit(UiState.Loading)
        _uploadProfileImageState.tryEmit((UiState.Empty))
        viewModelScope.launch {
            profileRepository.getProfile(object : ApiListener1<ProfileResponse> {
                override fun onSuccess(data1: ProfileResponse) {
                    _getProfileState.tryEmit(UiState.Success(data1))
                }

                override fun onFailure(message1: String) {
                    _getProfileState.tryEmit(UiState.Error(message1))
                }
            })
        }
    }

    fun uploadImageProfile(uri: Uri) {
        _uploadProfileImageState.tryEmit(UiState.Loading)
        viewModelScope.launch(Dispatchers.Default) {
            profileRepository.uploadProfileImage(uri, object : ApiListener1<String> {
                override fun onSuccess(data1: String) {
                    _uploadProfileImageState.tryEmit(UiState.Success(data1))
                }

                override fun onFailure(message2: String) {
                    _uploadProfileImageState.tryEmit(UiState.Error(message2))
                }

            })
        }
    }

    init {
        getProfile()
    }
}