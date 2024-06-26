package com.dicoding.capstone.data.model

import com.google.gson.annotations.SerializedName

data class CalculateQuizResponse(

	@field:SerializedName("data")
	val data: CalculateData,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)

data class CalculateData(

	@field:SerializedName("newLevel")
	val newLevel: Int,

	@field:SerializedName("newExp")
	val newExp: Int,

	@field:SerializedName("grade")
	val grade: Int,

	@field:SerializedName("expGain")
	val expGain: Int
)
