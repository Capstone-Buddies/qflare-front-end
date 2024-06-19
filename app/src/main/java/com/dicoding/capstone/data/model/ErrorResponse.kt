package com.dicoding.capstone.data.model

import com.google.gson.annotations.SerializedName

data class Response(

	@field:SerializedName("traces")
	val traces: List<TracesItem>,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)

data class ErrorsItem(

	@field:SerializedName("property")
	val property: String,

	@field:SerializedName("message")
	val message: String
)

data class TracesItem(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("errors")
	val errors: List<ErrorsItem>
)
