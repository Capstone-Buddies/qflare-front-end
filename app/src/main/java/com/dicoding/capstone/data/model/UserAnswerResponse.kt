package com.dicoding.capstone.data.model

import com.google.gson.annotations.SerializedName

data class UserAnswerResponse(

	@field:SerializedName("data")
	val data: UserAnswerData,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)

data class UserAnswerData(

	@field:SerializedName("answers")
	val answers: List<AnswersItem>
)

data class AnswersItem(

	@field:SerializedName("duration")
	val duration: Int,

	@field:SerializedName("correctness")
	val correctness: Boolean,

	@field:SerializedName("userAnswer")
	val userAnswer: Int,

	@field:SerializedName("question")
	val question: String,

	@field:SerializedName("option3")
	val option3: String,

	@field:SerializedName("option4")
	val option4: String,

	@field:SerializedName("option1")
	val option1: String,

	@field:SerializedName("option2")
	val option2: String
)
