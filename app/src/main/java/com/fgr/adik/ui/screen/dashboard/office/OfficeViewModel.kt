package com.fgr.adik.ui.screen.dashboard.office

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fgr.adik.data.listener.ApiListener1
import com.fgr.adik.data.listener.ApiListener2
import com.fgr.adik.data.response.GetOfficeByIdResponse
import com.fgr.adik.repository.AuthRepository
import com.fgr.adik.repository.OfficeRepository
import com.fgr.adik.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class OfficeScreenData(
    val officeId: String,
    val officeName: String,
    val officeImageUrl: String,
    val officeAccess: String,
    val officeDivision: String
)
@HiltViewModel
class OfficeViewModel
@Inject constructor(
    private val officeRepository: OfficeRepository,
    private val authRepository: AuthRepository,
) : ViewModel() {
    private val _getMyOfficeIdState = MutableStateFlow<UiState<List<OfficeScreenData>>>(UiState.Empty)
    val getOfficeIdState
        get() = _getMyOfficeIdState

    init {
        getMyOffice()
    }
    fun getMyOffice() {
        _getMyOfficeIdState.tryEmit(UiState.Loading)
        viewModelScope.launch {
            officeRepository.getMyOfficeId(
                apiListener2 = object : ApiListener2<List<String>> {
                    override fun onSuccess(data2: List<String>) {
                        viewModelScope.launch {
                            val officeList = mutableListOf<OfficeScreenData>()
                            data2.forEachIndexed { index, officeId ->
                                officeRepository.getOfficeById(
                                    officeId = officeId,
                                    apiListener1 = object : ApiListener1<GetOfficeByIdResponse> {
                                        override fun onSuccess(data1: GetOfficeByIdResponse) {
                                            officeList.add(
                                                OfficeScreenData(
                                                    officeId = officeId,
                                                    officeName = data1.name ?: "",
                                                    officeImageUrl = data1.officeImageUrl ?: "",
                                                    officeAccess = data1.role ?: "",
                                                    officeDivision = data1.division ?: ""
                                                )
                                            )
                                            if (index+1 == data2.size) {
                                                _getMyOfficeIdState.tryEmit(UiState.Success(officeList))
                                            }
                                        }
                                        override fun onFailure(message1: String) {
                                            _getMyOfficeIdState.tryEmit(UiState.Error(message1))
                                        }

                                    }
                                )
                            }
                        }
                    }

                    override fun onFailure(message2: String) {
                        _getMyOfficeIdState.tryEmit(UiState.Error(message2))
                    }

                }
            )
        }
    }
    fun logout(callback: () -> Unit) = authRepository.logout(callback)
}