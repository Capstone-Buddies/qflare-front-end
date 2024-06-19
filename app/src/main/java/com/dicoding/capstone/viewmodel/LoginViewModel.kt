package com.dicoding.capstone.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.capstone.data.model.LoginResponse
import com.dicoding.capstone.data.pref.UserModel
import com.dicoding.capstone.data.repository.UserRepository

class LoginViewModel(private val repository: UserRepository) : ViewModel() {

    var loginResult : MutableLiveData<LoginResponse> = repository.login
    var isLoading: LiveData<Boolean> = repository.isLoading


    fun login(email: String, password: String) {
        return repository.login(email, password)
    }

    suspend fun saveSession(user: UserModel) {
        repository.saveSession(user)
    }
}