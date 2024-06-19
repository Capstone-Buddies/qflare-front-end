package com.dicoding.capstone.data.pref

data class AnswerModel(
    val quizId: Int,
    val answers: List<Answer>
)

data class Answer(
    val questionId: Int,
    val userAnswer: Int, // valid values: 1, 2, 3, 4
    val duration: Int // in seconds
)