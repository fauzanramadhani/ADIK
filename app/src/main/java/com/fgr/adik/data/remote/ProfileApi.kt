package com.fgr.adik.data.remote

import com.fgr.adik.data.response.BaseResponse
import com.fgr.adik.data.response.ProfileResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PUT
import retrofit2.http.Part

interface ProfileApi {
    @FormUrlEncoded
    @PUT("/user/profile")
    suspend fun saveProfile(
        @Field("name") name: String,
        @Field("phoneNumber") phoneNumber: String,
        @Field("address") address: String,
    ): Response<BaseResponse<Any>>

    @GET("/user/profile")
    suspend fun getProfile() : Response<BaseResponse<ProfileResponse>>

    @Multipart
    @PUT("/user/profile/image")
    suspend fun uploadProfileImage(
        @Part file: MultipartBody.Part
    ): Response<BaseResponse<ProfileResponse>>
}