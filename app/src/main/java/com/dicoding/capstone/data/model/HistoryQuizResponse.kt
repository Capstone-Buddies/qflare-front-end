package com.dicoding.capstone.data.model

import com.google.gson.annotations.SerializedName

data class HistoryQuizResponse(

	@field:SerializedName("data")
	val data: HistoryData,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)

data class HistoriesItem(

	@field:SerializedName("level")
	val level: Int,

	@field:SerializedName("grade")
	val grade: Int,

	@field:SerializedName("quizCategory")
	val quizCategory: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("timestamp")
	val timestamp: String
)

data class HistoryData(

	@field:SerializedName("histories")
	val histories: List<HistoriesItem>
)
