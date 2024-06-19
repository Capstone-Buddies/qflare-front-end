package com.dicoding.capstone.data.pref

data class UserModel (
    val token:String,
    val username:String,
    val email:String,
    val schoolOrigin:String,
    val level:String,
    val exp:String,
    val profileImgUrl:String,
    val isLogin: Boolean = false
)


