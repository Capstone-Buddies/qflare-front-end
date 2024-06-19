package com.dicoding.capstone.data.network.requestBodyModel

import kotlinx.serialization.Serializable

@Serializable
data class GetQuizBody(
    val quizCategory: String,
)