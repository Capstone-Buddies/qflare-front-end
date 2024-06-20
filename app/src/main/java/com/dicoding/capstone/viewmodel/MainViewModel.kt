package com.dicoding.capstone.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dicoding.capstone.data.model.HistoryQuizResponse
import com.dicoding.capstone.data.model.UserResponse
import com.dicoding.capstone.data.pref.UserModel
import com.dicoding.capstone.data.repository.UserRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: UserRepository) : ViewModel() {

    var isLoading: LiveData<Boolean> = repository.isLoading

    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }

    fun getUserData(): LiveData<UserResponse?> {
        repository.getUserData()
        return repository.userData
    }
    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }
    fun getLastQuiz(): LiveData<HistoryQuizResponse>{
        repository.getHistoryQuizData()
        return repository.listHistoryQuiz
    }

}