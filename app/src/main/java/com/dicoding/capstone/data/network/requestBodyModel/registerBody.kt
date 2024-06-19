package com.dicoding.capstone.data.network.requestBodyModel
import kotlinx.serialization.Serializable

@Serializable
data class RegisterBody(
    val username: String,
    val email: String,
    val password: String,
    val schoolOrigin: String,
)