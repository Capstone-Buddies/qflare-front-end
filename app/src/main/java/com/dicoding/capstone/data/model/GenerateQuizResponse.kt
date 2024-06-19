package com.dicoding.capstone.data.model

import com.google.gson.annotations.SerializedName

data class GenerateQuizResponse(

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)

data class Data(

	@field:SerializedName("quizId")
	val quizId: String,

	@field:SerializedName("questions")
	val questions: List<QuestionsItem>
)

data class QuestionsItem(

	@field:SerializedName("question")
	val question: String,

	@field:SerializedName("option3")
	val option3: String,

	@field:SerializedName("option4")
	val option4: String,

	@field:SerializedName("option1")
	val option1: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("option2")
	val option2: String
)
