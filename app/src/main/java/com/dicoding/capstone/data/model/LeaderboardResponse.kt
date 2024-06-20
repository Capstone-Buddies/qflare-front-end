package com.dicoding.capstone.data.model

import com.google.gson.annotations.SerializedName

data class LeaderboardResponse(

	@field:SerializedName("data")
	val data: LeaderboardData,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)

data class LeaderboardData(

	@field:SerializedName("leaderboard")
	val leaderboard: List<LeaderboardItem>
)

data class LeaderboardItem(

	@field:SerializedName("level")
	val level: Int,

	@field:SerializedName("profileImgUrl")
	val profileImgUrl: String,

	@field:SerializedName("exp")
	val exp: Int,

	@field:SerializedName("username")
	val username: String
)
