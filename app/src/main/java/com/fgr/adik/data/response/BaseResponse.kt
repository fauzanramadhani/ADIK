package com.fgr.adik.data.response

import com.google.gson.annotations.SerializedName

data class BaseResponse<out T: Any?>(

	@field:SerializedName("status")
	val status: String,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("data")
	val data: T? = null,
)
