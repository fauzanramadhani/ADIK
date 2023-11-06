package com.fgr.adik.ui.screen.auth.on_boarding

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fgr.adik.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel
@Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    val firebaseCurrentUser = authRepository.currentUser()
    fun loginWithGoogle() = authRepository.loginWithGoogle()

    fun handleGoogleSignInResult(
        data: Intent,
        onComplete: (success: Boolean, message: String) -> Unit,
    ) {
        authRepository.handleGoogleSignInResult(
            data = data,
            onComplete = { task1Success, task1Data, task1Message ->
                if (task1Success) {
                    viewModelScope.launch {
                        authRepository.recordLogin(
                            email = task1Data?.providerData?.firstOrNull { it.providerId == "google.com" }?.email
                                ?: "",
                            firebaseUid = task1Data?.uid ?: "",
                            loginMethod = "google",
                            onComplete = onComplete
                        )
                    }
                } else {
                    onComplete(false, task1Message.toString())
                }
            }
        )
    }
}