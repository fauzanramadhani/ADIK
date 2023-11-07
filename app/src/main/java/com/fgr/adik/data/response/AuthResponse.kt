package com.fgr.adik.data.response

import com.google.gson.annotations.SerializedName

data class AuthResponse(

	@field:SerializedName("userMongoId")
	val userMongoId: String,
)