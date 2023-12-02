package com.fgr.adik.ui.screen.dashboard.office.dashboard_office

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fgr.adik.data.listener.ApiListener1
import com.fgr.adik.data.response.GetOfficeByIdResponse
import com.fgr.adik.repository.AuthRepository
import com.fgr.adik.repository.OfficeRepository
import com.fgr.adik.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardOfficeViewModel
@Inject constructor(
    private val officeRepository: OfficeRepository,
    private val authRepository: AuthRepository,
) : ViewModel() {
    private val _getOfficeByIdState = MutableStateFlow<UiState<GetOfficeByIdResponse>>(UiState.Empty)
    val getOfficeByIdState
        get() = _getOfficeByIdState

    fun getOfficeById(officeId: String) {
        _getOfficeByIdState.tryEmit(UiState.Loading)
        viewModelScope.launch {
            officeRepository.getOfficeById(
                officeId = officeId,
                apiListener1 = object : ApiListener1<GetOfficeByIdResponse> {
                    override fun onSuccess(data1: GetOfficeByIdResponse) {
                        _getOfficeByIdState.tryEmit(UiState.Success(data1))
                    }

                    override fun onFailure(message1: String) {
                        _getOfficeByIdState.tryEmit(UiState.Error(message1))
                    }

                }
            )
        }
    }
    fun logout(callback: () -> Unit) = authRepository.logout(callback)
}