package com.fgr.adik.data.remote

import com.fgr.adik.data.response.AuthResponse
import com.fgr.adik.data.response.BaseResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {
    @FormUrlEncoded
    @POST("/user/auth")
    suspend fun recordLogin(
        @Field("email") email: String,
        @Field("loginMethod") loginMethods: String,
        @Field("firebaseUid") firebaseUid: String,
    ) : Response<BaseResponse<AuthResponse>>
}