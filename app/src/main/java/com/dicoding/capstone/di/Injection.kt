package com.dicoding.capstone.di

import android.content.Context
import com.dicoding.capstone.data.network.ApiConfig
import com.dicoding.capstone.data.pref.UserPreference
import com.dicoding.capstone.data.pref.dataStore
import com.dicoding.capstone.data.repository.UserRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {   fun provideRepository(context: Context): UserRepository {
    val pref = UserPreference.getInstance(context.dataStore)
    val user = runBlocking { pref.getSession().first() }
    val apiService = ApiConfig.getApiService(user.token)
    return UserRepository.getInstance(apiService, pref)
}
}