package com.fgr.adik.data

import com.google.gson.annotations.SerializedName

data class BaseResponse(

	@field:SerializedName("status")
	val status: String,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("data")
	val data: Any? = null,
)
