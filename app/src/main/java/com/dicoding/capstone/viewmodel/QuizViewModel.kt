package com.dicoding.capstone.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.capstone.data.model.CalculateQuizResponse
import com.dicoding.capstone.data.model.GenerateQuizResponse
import com.dicoding.capstone.data.model.QuestionsItem
import com.dicoding.capstone.data.model.UserAnswerResponse
import com.dicoding.capstone.data.pref.AnswerModel
import com.dicoding.capstone.data.repository.UserRepository

class QuizViewModel(private val repository: UserRepository): ViewModel() {
    private var getQuizDataResult = MutableLiveData<List<QuestionsItem>>()
    var listQuiz: MutableLiveData<List<QuestionsItem>> = getQuizDataResult
//    lateinit var quizData: MutableLiveData<QuizData>

    fun generateQuiz(tipe: Int?): LiveData<GenerateQuizResponse> {
        repository.generateQuizData(tipe)
        return repository.listQuiz
    }

    fun getQuizId(): String{
        return repository.quizId
    }
    fun submitAnswer(answerModel: AnswerModel): LiveData<CalculateQuizResponse>{
        repository.getQuizResult(answerModel)
        return repository.quizResult
    }
    fun getAnswerHistory(id: Int): LiveData<UserAnswerResponse>{
        repository.getHistoryAnswer(id)
        return repository.historyAnswer
    }
}