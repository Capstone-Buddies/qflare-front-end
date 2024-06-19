package com.dicoding.capstone.data.network.requestBodyModel
import kotlinx.serialization.Serializable

@Serializable
data class LoginBody(
    val email: String,
    val password: String
)