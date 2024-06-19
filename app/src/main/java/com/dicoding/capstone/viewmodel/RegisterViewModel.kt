package com.dicoding.capstone.viewmodel

import androidx.lifecycle.ViewModel
import com.dicoding.capstone.data.model.RegisterResponse
import com.dicoding.capstone.data.repository.UserRepository

class RegisterViewModel(private val repository: UserRepository) : ViewModel() {

    suspend fun register(name: String, email: String, password: String, schoolOrigin: String): RegisterResponse {
        return repository.register(name, email, password, schoolOrigin)
    }
}