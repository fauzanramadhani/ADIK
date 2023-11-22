package com.fgr.adik.ui.screen.dashboard.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fgr.adik.data.response.ProfileResponse
import com.fgr.adik.repository.ApiListener
import com.fgr.adik.repository.AuthRepository
import com.fgr.adik.repository.ProfileRepository
import com.fgr.adik.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
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

    val isLoginMethodGoogle = firebaseCurrentUser()?.providerData?.firstOrNull {
        it.providerId == "google.com"
    }?.email != null

    fun firebaseCurrentUser() = authRepository.currentUser()

    fun logout(callback: () -> Unit) = authRepository.logout(callback)

    fun getProfile() {
        _getProfileState.tryEmit(UiState.Loading)
        viewModelScope.launch {
            profileRepository.getProfile(object : ApiListener<ProfileResponse> {
                override fun onSuccess(data: ProfileResponse) {
                    _getProfileState.tryEmit(UiState.Success(data))
                }

                override fun onFailure(message: String) {
                    _getProfileState.tryEmit(UiState.Error(message))
                }
            })
        }
    }

    init {
        getProfile()
    }
}