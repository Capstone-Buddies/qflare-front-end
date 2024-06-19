package com.dicoding.capstone.network

import com.dicoding.capstone.data.model.HistoryQuizResponse
import com.dicoding.capstone.data.model.LoginResponse
import com.dicoding.capstone.data.model.RegisterResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("sekolah") schoolOrigin: String
    ): RegisterResponse

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @GET("history")
    fun gethistories(): Call<HistoryQuizResponse>

//    @Multipart
//    @POST("history")
//    suspend fun uploadImage(
//        @Part file: MultipartBody.Part,
//        @Part("description") description: RequestBody,
//    ): UpResponse

}