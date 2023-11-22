package com.fgr.adik.ui.screen.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fgr.adik.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    fun login(
        email: String, password: String, onComplete: (success: Boolean, message: String) -> Unit
    ) {
        authRepository.loginWithEmail(email, password) { success, data, message ->
            if (success) {
                viewModelScope.launch {
                    authRepository.recordLogin(
                        email = data?.email ?: "",
                        firebaseUid = data?.uid ?: "",
                        loginMethod = "email",
                        onComplete = onComplete
                    )
                }
            } else {
                onComplete(false, message)
            }
        }
    }
}