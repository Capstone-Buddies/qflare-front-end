package com.dicoding.capstone.data.pref

data class LoginModel (
    val token:String,
    val email:String,
    val isLogin: Boolean = false
)