package com.fgr.adik.repository

import android.app.Application
import android.util.Log
import com.fgr.adik.R
import com.fgr.adik.data.remote.ProfileApi
import com.fgr.adik.data.response.BaseResponse
import com.fgr.adik.data.response.ProfileResponse
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
            onComplete(false, appContext.getString(R.string.server_failed))
        }
    }

    suspend fun getProfile(
        apiListener: ApiListener<ProfileResponse>
    ) {
        try {
            val result = profileApi.getProfile()
            if (result.isSuccessful && result.body() != null) {
                apiListener.onSuccess(result.body()!!.data!!)
            } else {
                val message =
                    Gson().fromJson(result.errorBody()?.string(), BaseResponse::class.java).message
                apiListener.onFailure(message)
            }
        } catch (e: Exception) {
            Log.e("getProfileRepo", e.message.toString())
            apiListener.onFailure(appContext.getString(R.string.server_failed))
        }
    }
}

interface ApiListener<T : Any> {
    fun onSuccess(data: T)
    fun onFailure(message: String)
}