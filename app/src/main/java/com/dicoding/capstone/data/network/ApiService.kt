package com.dicoding.capstone.data.network

import com.dicoding.capstone.data.model.CalculateQuizResponse
import com.dicoding.capstone.data.model.GenerateQuizResponse
import com.dicoding.capstone.data.model.HistoryQuizResponse
import com.dicoding.capstone.data.model.LeaderboardResponse
import com.dicoding.capstone.data.model.LoginResponse
import com.dicoding.capstone.data.model.LogoutResponse
import com.dicoding.capstone.data.model.RegisterResponse
import com.dicoding.capstone.data.model.UpdateProfilResponse
import com.dicoding.capstone.data.model.UserAnswerResponse
import com.dicoding.capstone.data.model.UserResponse
import com.dicoding.capstone.data.network.requestBodyModel.GetQuizBody
import com.dicoding.capstone.data.network.requestBodyModel.LoginBody
import com.dicoding.capstone.data.network.requestBodyModel.RegisterBody
import com.dicoding.capstone.data.pref.AnswerModel
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {
    //    @FormUrlEncoded
//    @POST("auth/register")
//    suspend fun register(
//        @Field("username") name: String,
//        @Field("email") email: String,
//        @Field("password") password: String,
//        @Field("schoolOrigin") schoolOrigin: String,
//    ): RegisterResponse
    @Headers("Content-Type: application/json")
    @POST("auth/register")
    suspend fun register(@Body registerBody: RegisterBody):RegisterResponse

    //    @FormUrlEncoded
//    @POST("auth/login")
//    fun login(
//        @Field("email") email: String,
//        @Field("password") password: String
//    ): Call<LoginResponse>
    @Headers("Content-Type: application/json")
    @POST("auth/login")
    fun login(@Body requestBody: LoginBody): Call<LoginResponse>

    // panggil data user setelah login
//    @FormUrlEncoded
//    @GET("users/my-profile")
//    fun getUserData(): Call<UserResponse>
    @Headers("Content-Type: application/json")
    @GET("users/my-profile")
    fun getUserData(): Call<UserResponse>

    // panggil api logout
    @Headers("Content-Type: application/json")
    @GET("auth/logout")
    fun logout(): Call<LogoutResponse>

    // panggil data Leaderboard
//    @FormUrlEncoded
//    @GET("users/leaderboard")
//    fun getUsersLeaderboard(): Call<LeaderboardResponse>
    @Headers("Content-Type: application/json")
    @GET("users/leaderboard")
    fun getUsersLeaderboard(): Call<LeaderboardResponse>

    // dipanggil waktu select quiz ( isinya quizCategory Literasi / TPS )
//    @FormUrlEncoded
//    @POST("quizzes")
//    fun getQuizzes(
//        @Field("quizCategory") quizCategory: String
//    ): Call<GenerateQuizResponse>
    @Headers("Content-Type: application/json")
    @POST("quizzes")
    fun getQuizzes(@Body requestBody: GetQuizBody): Call<GenerateQuizResponse>

    // dipanggil waktu submit jawaban quiz
//    @FormUrlEncoded
//    @POST("quizzes/result")
//    fun getQuizResult(
//        @Body quizAnswers: AnswerModel
//    ): Call<CalculateQuizResponse>
    @Headers("Content-Type: application/json")
    @POST("quizzes/result")
    fun getQuizResult(
        @Body quizAnswers: AnswerModel
    ): Call<CalculateQuizResponse>

    // dipanggil waktu akses history quiz menu
//    @FormUrlEncoded
//    @GET("quizzes/histories")
//    fun getQuizHistories(): Call<HistoryQuizResponse>
    @Headers("Content-Type: application/json")
    @GET("quizzes/histories")
    fun getQuizHistories(): Call<HistoryQuizResponse>

    // dipanggil waktu akses history quiz detail
    @Headers("Content-Type: application/json")
    @GET("quizzes/histories/{historyId}/answers")
    fun getQuizHistoryDetail(
        @Path("historyId") historyId: Int
    ): Call<UserAnswerResponse>

    @Headers("Content-Type: application/json")
    @POST("users/my-profile/image")
    suspend fun uploadImage(
        @Part file: MultipartBody.Part,
        @Body jsonBody: String
    ): Call<UpdateProfilResponse>




}


