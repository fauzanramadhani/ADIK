package com.fgr.adik.hilt.module

import android.app.Application
import com.fgr.adik.shared_pref.USER_MONGO_ID_KEY
import com.fgr.adik.shared_pref.SharedPreferencesManager
import com.fgr.adik.hilt.qualifier.BearerToken
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object TokenModule {
    @Provides
    @ViewModelScoped
    @BearerToken
    fun provideBearerToken(appContext: Application): String {
        return SharedPreferencesManager(appContext).getString(USER_MONGO_ID_KEY)
    }
}