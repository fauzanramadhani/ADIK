package com.fgr.adik.ui.screen.auth.login

import androidx.lifecycle.ViewModel
import com.fgr.adik.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
    @Inject constructor(
        authRepository: AuthRepository,
    ) : ViewModel() {
}