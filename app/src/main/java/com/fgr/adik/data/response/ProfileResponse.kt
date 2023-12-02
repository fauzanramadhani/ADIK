package com.fgr.adik.data.response

import com.google.gson.annotations.SerializedName

data class ProfileResponse(

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("imageProfileUrl")
    val imageProfileUrl: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("phoneNumber")
    val phoneNumber: String? = null,

    @field:SerializedName("address")
    val address: String? = null,

    @field:SerializedName("createdAt")
    val createdAt: String? = null,
)