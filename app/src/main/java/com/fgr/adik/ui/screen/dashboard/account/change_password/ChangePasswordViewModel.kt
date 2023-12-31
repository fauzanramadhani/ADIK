package com.fgr.adik.ui.screen.dashboard.account.change_password

import androidx.lifecycle.ViewModel
import com.fgr.adik.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {
    fun changePassword(
        oldPassword: String,
        newPassword: String,
        callback: (success: Boolean, message: String) -> Unit,
    ) = authRepository.changeChangePassword(
        oldPassword,
        newPassword,
        callback,
    )
}