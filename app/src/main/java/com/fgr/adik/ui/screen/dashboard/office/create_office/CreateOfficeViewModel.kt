package com.fgr.adik.ui.screen.dashboard.office.create_office

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fgr.adik.data.listener.ApiListener1
import com.fgr.adik.data.listener.ApiListener2
import com.fgr.adik.data.request_body.ReqCreateOffice
import com.fgr.adik.data.response.GetOfficeByIdResponse
import com.fgr.adik.repository.OfficeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateOfficeViewModel
@Inject constructor(
    private val officeRepository: OfficeRepository,
) : ViewModel() {
    fun createOffice(
        reqCreateOffice: ReqCreateOffice,
        officeImageUri: Uri,
        callback: (success: Boolean, data: GetOfficeByIdResponse?, message: String) -> Unit,
    ) {
        viewModelScope.launch {
            officeRepository.createOffice(
                reqCreateOffice = reqCreateOffice,
                apiListener1 = object : ApiListener1<String> {
                    override fun onSuccess(data1: String) {
                        viewModelScope.launch {
                            if (data1.isEmpty()) {
                                callback(false, null, "404")
                            } else {
                                officeRepository.uploadOfficeImage(
                                    officeId = data1,
                                    uri = officeImageUri,
                                    object : ApiListener2<String> {
                                        override fun onSuccess(data2: String) {
                                            callback(
                                                true, GetOfficeByIdResponse(
                                                    officeId = data1,
                                                    officeImageUrl = data2
                                                ), "201"
                                            )
                                        }

                                        override fun onFailure(message2: String) {
                                            callback(false, null, message2)
                                        }

                                    }
                                )
                            }
                        }
                    }

                    override fun onFailure(message1: String) {
                        callback(false, null, message1)
                    }

                }
            )
        }
    }
}