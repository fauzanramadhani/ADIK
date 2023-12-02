package com.fgr.adik.repository

import android.app.Application
import android.net.Uri
import android.util.Log
import com.fgr.adik.R
import com.fgr.adik.data.listener.ApiListener1
import com.fgr.adik.data.remote.ProfileApi
import com.fgr.adik.data.response.BaseResponse
import com.fgr.adik.data.response.ProfileResponse
import com.fgr.adik.utils.createImageAsFormReqBody
import com.fgr.adik.utils.getErrorMessage
import com.fgr.adik.utils.uriToFile
import com.google.gson.Gson
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val appContext: Application,
    private val profileApi: ProfileApi,
) {
    suspend fun saveProfile(
        name: String,
        phoneNumber: String,
        address: String,
        onComplete: (success: Boolean, message: String) -> Unit
    ) {
        try {
            val result = profileApi.saveProfile(
                name,
                phoneNumber,
                address
            )
            if (result.isSuccessful) {
                onComplete(
                    true,
                    appContext.getString(R.string.screen_edit_profile_saved_successfully)
                )
            } else {
                val message =
                    Gson().fromJson(result.errorBody()?.string(), BaseResponse::class.java).message
                onComplete(false, message)
            }
        } catch (e: Exception) {
            Log.e("getProfileRepo", e.message.toString())
            onComplete(false, getErrorMessage(appContext, e.message.toString()))
        }
    }

    suspend fun getProfile(
        apiListener1: ApiListener1<ProfileResponse>
    ) {
        try {
            val result = profileApi.getProfile()
            if (result.isSuccessful && result.body() != null) {
                apiListener1.onSuccess(result.body()!!.data!!)
            } else {
                val message =
                    Gson().fromJson(result.errorBody()?.string(), BaseResponse::class.java).message
                apiListener1.onFailure(message)
            }
        } catch (e: Exception) {
            Log.e("ProfileRepo", e.message.toString())
            apiListener1.onFailure(getErrorMessage(appContext, e.message.toString()))
        }
    }

    suspend fun uploadProfileImage(
        uri: Uri,
        apiListener1: ApiListener1<String>,
    ) {
        try {
            val myFile = uriToFile(uri, appContext)
            val profileImage = createImageAsFormReqBody(myFile, "imageProfile")
            val result = profileApi.uploadProfileImage(
                file = profileImage
            )
            if (result.isSuccessful && result.body() != null) {
                apiListener1.onSuccess(result.body()?.data?.imageProfileUrl ?: "")
            } else {
                val message =
                    Gson().fromJson(result.errorBody()?.string(), BaseResponse::class.java).message
                apiListener1.onFailure(message)
            }
        } catch (e: Exception) {
            Log.e("ProfileRepo", e.message.toString())
            apiListener1.onFailure(getErrorMessage(appContext, e.message.toString()))
        }
    }
}