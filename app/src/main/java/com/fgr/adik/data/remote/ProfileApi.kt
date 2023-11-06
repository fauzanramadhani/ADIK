package com.fgr.adik.data.remote

import com.fgr.adik.data.response.BaseResponse
import com.fgr.adik.data.response.ProfileResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ProfileApi {
    @FormUrlEncoded
    @POST("/user/profile")
    suspend fun saveProfile(
        @Field("imageProfileUrl") imageProfileUrl: String,
        @Field("name") name: String,
        @Field("phoneNumber") phoneNumber: String,
        @Field("address") address: String,
    ) : Response<BaseResponse<ProfileResponse>>
}