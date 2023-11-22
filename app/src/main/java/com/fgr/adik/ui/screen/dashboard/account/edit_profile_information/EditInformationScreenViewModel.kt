package com.fgr.adik.ui.screen.dashboard.account.edit_profile_information

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
class EditInformationScreenViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val profileRepository: ProfileRepository,
) : ViewModel() {
    private val _getProfileState = MutableStateFlow<UiState<ProfileResponse>>(UiState.Empty)
    val getProfileState
        get() = _getProfileState.asStateFlow()

    init {
        getProfileInformation()
    }

    fun logout(callback: () -> Unit) = authRepository.logout(callback)

    fun saveProfile(
        name: String,
        phoneNumber: String,
        address: String,
        callback: (success: Boolean, message: String) -> Unit,
    ) {
        viewModelScope.launch {
            profileRepository.saveProfile(
                name,
                phoneNumber,
                address
            ) { success, message ->
                callback(success, message)
            }
        }
    }

    private fun getProfileInformation() {
        viewModelScope.launch {
            _getProfileState.tryEmit(UiState.Loading)
            profileRepository.getProfile(object : ApiListener<ProfileResponse> {
                override fun onSuccess(data: ProfileResponse) {
                    _getProfileState.tryEmit(UiState.Success(data))
                }

                override fun onFailure(message: String) {
                    _getProfileState.tryEmit((UiState.Error(message)))
                }

            })
        }
    }
}