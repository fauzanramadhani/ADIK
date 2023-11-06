package com.fgr.adik.ui.screen.auth.login

import androidx.lifecycle.ViewModel
import com.fgr.adik.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    fun login(
        email: String,
        password: String,
        onComplete: (success: Boolean, message: String) -> Unit
    ) = authRepository.loginWithEmail(email, password, onComplete)
}