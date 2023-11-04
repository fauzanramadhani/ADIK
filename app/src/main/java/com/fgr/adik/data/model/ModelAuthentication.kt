package com.fgr.adik.data.model

import com.google.gson.annotations.SerializedName

data class ModelAuthentication(
	@field:SerializedName("_id")
	val mongoId: String,

	@field:SerializedName("firebaseUid")
	val firebaseUid: List<String>,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("loginMethod")
	val loginMethod: List<String>,

	@field:SerializedName("emailVerified")
	val emailVerified: Boolean,

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String,
)
