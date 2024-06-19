package com.dicoding.capstone.data.model

import com.google.gson.annotations.SerializedName

data class Response(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)