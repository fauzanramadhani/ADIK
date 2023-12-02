package com.fgr.adik.data.response

import com.google.gson.annotations.SerializedName

data class GetOfficeByIdResponse(

    @field:SerializedName("officeId")
    val officeId: String? = null,

    @field:SerializedName("role")
    val role: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("officeImageUrl")
    val officeImageUrl: String? = null,

    @field:SerializedName("address")
    val address: String? = null,

    @field:SerializedName("division")
    val division: String? = null,
)