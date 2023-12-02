package com.fgr.adik.data.remote

import com.fgr.adik.data.request_body.ReqCreateOffice
import com.fgr.adik.data.response.BaseResponse
import com.fgr.adik.data.response.GetOfficeByIdResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface OfficeApi {
    @POST("office/create")
    suspend fun createOffice(
        @Body reqCreateOffice: ReqCreateOffice,
    ): Response<BaseResponse<String>>

    @Multipart
    @PUT("office/image/{officeId}")
    suspend fun uploadOfficeImage(
        @Path("officeId") officeId: String,
        @Part file: MultipartBody.Part,
    ): Response<BaseResponse<String>>

    @GET("office/{officeId}")
    suspend fun getMyOfficeById(
        @Path("officeId") officeId: String,
    ): Response<BaseResponse<GetOfficeByIdResponse>>

    @GET("office/")
    suspend fun getMyOfficeId(): Response<BaseResponse<List<String>>>
}