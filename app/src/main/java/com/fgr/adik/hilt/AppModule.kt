package com.fgr.adik.hilt

import android.app.Application
import android.content.Context
import com.fgr.adik.repository.AuthRepository
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
    ) = AuthRepository (
        appContext = appContext,
    )
}