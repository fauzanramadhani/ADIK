package com.fgr.adik.ui.screen.dashboard.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fgr.adik.data.listener.ApiListener1
import com.fgr.adik.data.response.ProfileResponse
import com.fgr.adik.repository.AuthRepository
import com.fgr.adik.repository.ProfileRepository
import com.fgr.adik.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BaseScreenViewModel
@Inject constructor(
    private val profileRepository: ProfileRepository,
    private val authRepository: AuthRepository,
) : ViewModel() {
    private val _hasProfile = MutableStateFlow<UiState<Boolean>>(UiState.Empty)
    val hasProfile
        get() = _hasProfile.asStateFlow()

    init {
        isProfileInformationIsNotEmpty()
    }

    fun isProfileInformationIsNotEmpty() = viewModelScope.launch {
        _hasProfile.tryEmit(UiState.Loading)
        profileRepository.getProfile(object : ApiListener1<ProfileResponse> {
            override fun onSuccess(data1: ProfileResponse) {
                if (data1.name == null || data1.address == null || data1.phoneNumber == null) {
                    _hasProfile.tryEmit(UiState.Success(false))
                } else {
                    _hasProfile.tryEmit(UiState.Success(true))
                }
            }

            override fun onFailure(message1: String) {
                _hasProfile.tryEmit(UiState.Error(message1))
            }

        })
    }

    fun logout(callback: () -> Unit) = authRepository.logout(callback)
}