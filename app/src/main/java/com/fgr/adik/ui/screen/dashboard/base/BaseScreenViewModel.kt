package com.fgr.adik.ui.screen.dashboard.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fgr.adik.data.response.ProfileResponse
import com.fgr.adik.repository.ApiListener
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
        profileRepository.getProfile(object : ApiListener<ProfileResponse> {
            override fun onSuccess(data: ProfileResponse) {
                if (data.name == null || data.address == null || data.phoneNumber == null) {
                    _hasProfile.tryEmit(UiState.Success(false))
                } else {
                    _hasProfile.tryEmit(UiState.Success(true))
                }
            }

            override fun onFailure(message: String) {
                _hasProfile.tryEmit(UiState.Error(message))
            }

        })
    }

    fun logout(callback: () -> Unit) = authRepository.logout(callback)
}