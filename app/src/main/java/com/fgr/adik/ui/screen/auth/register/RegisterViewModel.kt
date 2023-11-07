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
            onComplete = { tak1Success, task2Data, tak1Message ->
                if (tak1Success) {
                    viewModelScope.launch {
                        authRepository.recordLogin(
                            email = task2Data?.email ?: "",
                            firebaseUid = task2Data?.uid ?: "",
                            loginMethod = "email",
                            onComplete = onComplete
                        )
                    }
                } else {
                    onComplete(false, tak1Message)
                }
            }
        )
    }
}