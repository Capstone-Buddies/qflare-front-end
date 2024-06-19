package com.dicoding.capstone.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.capstone.data.model.LeaderboardItem
import com.dicoding.capstone.data.repository.UserRepository

class LeaderViewModel(private val repository: UserRepository): ViewModel() {
    private var getLeaderboardResult = MutableLiveData<List<LeaderboardItem>>()
    var listLeaderboard: MutableLiveData<List<LeaderboardItem>> = getLeaderboardResult


    fun getLeaderboardData(): LiveData<List<LeaderboardItem>> {
        repository.getLeaderboardData()
        return repository.listLeaderboard
    }
}