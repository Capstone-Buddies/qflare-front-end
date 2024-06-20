package com.dicoding.capstone.data.model

import com.google.gson.annotations.SerializedName

data class UserResponse(

	@field:SerializedName("data")
	val data: UserData,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)

data class UserData(

	@field:SerializedName("schoolOrigin")
	val schoolOrigin: String,

	@field:SerializedName("level")
	val level: Int,

	@field:SerializedName("profileImgUrl")
	val profileImgUrl: String,

	@field:SerializedName("exp")
	val exp: Int,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("username")
	val username: String
)
