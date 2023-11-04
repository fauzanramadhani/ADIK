package com.fgr.adik.data.model

import com.google.gson.annotations.SerializedName

data class ModelProfile(
    @field:SerializedName("_id")
    val id: String,

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

    @field:SerializedName("updatedAt")
    val updatedAt: String,
)