package com.fgr.adik.data.config

import com.fgr.adik.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun createRetrofit(
    baseUrl: String = BuildConfig.base_url,
    headerAuthorization: String = ""
): Retrofit {

    val authorizationInterceptor = Interceptor { chain ->
        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder()
            .header("Authorization", "Bearer $headerAuthorization")
            .build()
        chain.proceed(newRequest)
    }

    val createOkHttp = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .addInterceptor(authorizationInterceptor)
        .build()

    return Retrofit.Builder()
        // Errornya disini
        // java.lang.IllegalArgumentException: Expected URL scheme 'http' or 'https' but no scheme was found for
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(createOkHttp)
        .build()
}