package com.dicoding.capstone.data.model

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)

data class Data(

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("username")
	val username: String
)
