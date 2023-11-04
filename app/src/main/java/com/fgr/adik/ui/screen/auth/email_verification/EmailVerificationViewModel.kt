package com.fgr.adik.ui.screen.auth.email_verification

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import com.fgr.adik.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class EmailVerificationViewModel
@Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {
    private var _currentCountDown = MutableStateFlow(0L)
    val currentCountDown
        get() = _currentCountDown

    private var countDownTimer: CountDownTimer? = null

    fun startCountDown(countDownInSeconds: Long) {
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

    override fun onCleared() {
        super.onCleared()
        countDownTimer?.cancel()
    }
}