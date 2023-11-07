package com.fgr.adik.repository

import android.app.Application
import com.fgr.adik.data.remote.ProfileApi
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val appContext: Application,
    private val profileApi: ProfileApi,
) {
    //
}