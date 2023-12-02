package com.fgr.adik.repository

import android.app.Application
import android.net.Uri
import android.util.Log
import com.fgr.adik.data.listener.ApiListener1
import com.fgr.adik.data.listener.ApiListener2
import com.fgr.adik.data.remote.OfficeApi
import com.fgr.adik.data.request_body.ReqCreateOffice
import com.fgr.adik.data.response.BaseResponse
import com.fgr.adik.data.response.GetOfficeByIdResponse
import com.fgr.adik.utils.createImageAsFormReqBody
import com.fgr.adik.utils.getErrorMessage
import com.fgr.adik.utils.uriToFile
import com.google.gson.Gson
import javax.inject.Inject

class OfficeRepository @Inject constructor(
    private val appContext: Application,
    private val officeApi: OfficeApi,
) {
    suspend fun createOffice(
        reqCreateOffice: ReqCreateOffice,
        apiListener1: ApiListener1<String>,
    ) {
        try {
            val result = officeApi.createOffice(
                reqCreateOffice
            )
            if (result.isSuccessful&& result.body() != null) {
                apiListener1.onSuccess(result.body()!!.data!!)
            } else {
                val message =
                    Gson().fromJson(result.errorBody()?.string(), BaseResponse::class.java).message
                apiListener1.onFailure(message)
            }
        } catch (e: Exception) {
            Log.e("OfficeRepo", e.message.toString())
            apiListener1.onFailure(getErrorMessage(appContext, e.message.toString()))
        }
    }

    suspend fun uploadOfficeImage(
        officeId: String,
        uri: Uri,
        apiListener2: ApiListener2<String>,
    ) {
        try {
            val myFile = uriToFile(uri, appContext)
            val profileImage = createImageAsFormReqBody(myFile, "imageOffice")
            val result = officeApi.uploadOfficeImage(
                officeId = officeId,
                file = profileImage
            )
            if (result.isSuccessful && result.body() != null) {
                apiListener2.onSuccess(result.body()?.data ?: "")
            } else {
                val message =
                    Gson().fromJson(result.errorBody()?.string(), BaseResponse::class.java).message
                apiListener2.onFailure(message)
            }
        } catch (e: Exception) {
            Log.e("ProfileRepo", e.message.toString())
            apiListener2.onFailure(getErrorMessage(appContext, e.message.toString()))
        }
    }

    suspend fun getOfficeById(
        officeId: String,
        apiListener1: ApiListener1<GetOfficeByIdResponse>,
    ) {
        try {
            val result = officeApi.getMyOfficeById(
                officeId = officeId
            )
            if (result.isSuccessful && result.body() != null) {
                apiListener1.onSuccess(result.body()?.data!!)
            } else {
                val message =
                    Gson().fromJson(result.errorBody()?.string(), BaseResponse::class.java).message
                apiListener1.onFailure(message)
            }
        } catch (e: Exception) {
            Log.e("OfficeRepo", e.message.toString())
            apiListener1.onFailure(getErrorMessage(appContext, e.message.toString()))
        }
    }

    suspend fun getMyOfficeId(
        apiListener2: ApiListener2<List<String>>
    ) {
        try {
            val result = officeApi.getMyOfficeId()
            if (result.isSuccessful && result.body() != null) {
                val myOfficeIdList = result.body()?.data ?: listOf()
                apiListener2.onSuccess(myOfficeIdList)
            } else {
                val message =
                    Gson().fromJson(result.errorBody()?.string(), BaseResponse::class.java).message
                apiListener2.onFailure(message)
            }
        } catch (e: Exception) {
            Log.e("OfficeRepo", e.message.toString())
            apiListener2.onFailure(getErrorMessage(appContext, e.message.toString()))
        }
    }
}