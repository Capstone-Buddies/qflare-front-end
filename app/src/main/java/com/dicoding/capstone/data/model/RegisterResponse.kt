package com.dicoding.capstone.data.model

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

	@field:SerializedName("data")
	val data: RegisterData,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)

data class RegisterData(

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("username")
	val username: String
)
