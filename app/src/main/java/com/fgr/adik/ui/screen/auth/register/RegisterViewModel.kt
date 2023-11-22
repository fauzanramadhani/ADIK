package com.fgr.adik.ui.screen.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fgr.adik.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel
@Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    fun registerWithEmail(
        email: String,
        password: String,
        onComplete: (success: Boolean, message: String) -> Unit,
    ) {
        authRepository.registerWithEmail(
            email = email,
            password = password,
            onComplete = { success, data, message ->
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
        )
    }
}