package com.fgr.adik.data.response

import com.google.gson.annotations.SerializedName

data class ProfileResponse(

    @field:SerializedName("imageProfileUrl")
    val imageProfileUrl: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("phoneNumber")
    val phoneNumber: String,

    @field:SerializedName("address")
    val address: String,

    @field:SerializedName("createdAt")
    val createdAt: String,
)