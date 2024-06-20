package com.dicoding.capstone.data.model

import com.google.gson.annotations.SerializedName

data class LogoutResponse(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)
