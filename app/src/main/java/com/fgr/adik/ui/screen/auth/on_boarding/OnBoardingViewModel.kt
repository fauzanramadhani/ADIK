package com.fgr.adik.ui.screen.auth.on_boarding

import android.content.Intent
import androidx.lifecycle.ViewModel
import com.fgr.adik.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel
@Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    val currentUser = authRepository.currentUser()
    val isEmailVerified = authRepository.isEmailVerified()
    fun loginWithGoogle() = authRepository.loginWithGoogle()

    fun handleGoogleSignInResult(
        data: Intent,
        callback: (success: Boolean) -> Unit,
    ) {
        authRepository.handleGoogleSignInResult(
            data = data,
            onComplete = { success ->
                callback(success)
            }
        )
    }
}