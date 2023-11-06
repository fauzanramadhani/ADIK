package com.fgr.adik.hilt.module

import android.app.Application
import com.fgr.adik.data.config.createRetrofit
import com.fgr.adik.data.remote.AuthApi
import com.fgr.adik.data.remote.ProfileApi
import com.fgr.adik.hilt.qualifier.BearerToken
import com.fgr.adik.repository.AuthRepository
import com.fgr.adik.repository.ProfileRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideAuthRepository(
        appContext: Application,
        @BearerToken token: String,
    ) = AuthRepository (
        appContext = appContext,
        authApi = createRetrofit(headerAuthorization = token).create(AuthApi::class.java)
    )
    @Provides
    @Singleton
    fun provideProfileRepository(
        appContext: Application,
        @BearerToken token: String,
    ) = ProfileRepository (
        appContext = appContext,
        profileApi = createRetrofit(headerAuthorization = token).create(ProfileApi::class.java)
    )
}