package com.fgr.adik.ui.screen.auth.email_verification

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import com.fgr.adik.repository.AuthRepository
import com.fgr.adik.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class EmailVerificationViewModel
@Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {
    fun firebaseCurrentUser() = authRepository.currentUser()
    private var _currentCountDown = MutableStateFlow(0L)
    val currentCountDown
        get() = _currentCountDown

    private var countDownTimer: CountDownTimer? = null
    private val _sendEmailState = MutableStateFlow<UiState<String>>(UiState.Empty)
    val sendEmailState
        get() = _sendEmailState

    private fun startCountDown(countDownInSeconds: Long) {
        countDownTimer?.cancel()
        countDownTimer = object : CountDownTimer(countDownInSeconds * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _currentCountDown.value = millisUntilFinished / 1000
            }

            override fun onFinish() {
                _currentCountDown.value = 0
            }
        }.start()
    }

    fun sendEmailVerification() {
        startCountDown(60L)
        sendEmailState.tryEmit(UiState.Loading)
        authRepository.sendEmailVerification { success, message ->
            if (success) {
                _sendEmailState.tryEmit(UiState.Success(message))
            } else {
                _sendEmailState.tryEmit(UiState.Error(message))
            }
        }
    }

    fun logout() {
        authRepository.logout()
    }

    override fun onCleared() {
        super.onCleared()
        countDownTimer?.cancel()
    }

    init {
        if (firebaseCurrentUser()?.isEmailVerified == false) {
            sendEmailVerification()
        }
    }
}