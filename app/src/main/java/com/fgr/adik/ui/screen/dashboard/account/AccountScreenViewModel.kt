package com.fgr.adik.ui.screen.dashboard.account

import androidx.lifecycle.ViewModel
import com.fgr.adik.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AccountScreenViewModel
@Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    fun logout() = authRepository.logout()
}