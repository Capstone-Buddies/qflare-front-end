package com.dicoding.capstone.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.capstone.data.network.ApiService
import com.dicoding.capstone.data.model.CalculateQuizResponse
import com.dicoding.capstone.data.model.GenerateQuizResponse
import com.dicoding.capstone.data.model.HistoryQuizResponse
import com.dicoding.capstone.data.model.LeaderboardItem
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
import com.dicoding.capstone.data.pref.UserModel
import com.dicoding.capstone.data.pref.UserPreference
import kotlinx.coroutines.flow.Flow
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class UserRepository private constructor(
    private val apiService: ApiService,
    private val userPreference: UserPreference
) {
//    private var Alist = MutableLiveData<List<ListStoryItem>>()
//    var list: MutableLiveData<List<ListStoryItem>> = Alist

    private var loginResult = MutableLiveData<LoginResponse>()
    var login: MutableLiveData<LoginResponse> = loginResult
    private var getUserDataResult = MutableLiveData<UserResponse>()
    var userData: MutableLiveData<UserResponse> = getUserDataResult
    private var getLeaderboardResult = MutableLiveData<List<LeaderboardItem>>()
    var listLeaderboard: MutableLiveData<List<LeaderboardItem>> = getLeaderboardResult
    private var getQuizDataResult = MutableLiveData<GenerateQuizResponse>()
    var listQuiz: MutableLiveData<GenerateQuizResponse> = getQuizDataResult
    var quizId=""
    private var getSubmitQuizAnswerResult = MutableLiveData<CalculateQuizResponse>()
    var quizResult: MutableLiveData<CalculateQuizResponse> = getSubmitQuizAnswerResult
    private var getHistoryQuiz = MutableLiveData<HistoryQuizResponse>()
    var listHistoryQuiz: MutableLiveData<HistoryQuizResponse> = getHistoryQuiz
    private var getHistoryAnswer = MutableLiveData<UserAnswerResponse>()
    var historyAnswer: MutableLiveData<UserAnswerResponse> = getHistoryAnswer
    private var getUploadResponse = MutableLiveData<UpdateProfilResponse>()
    var uploadResponse: MutableLiveData<UpdateProfilResponse> = getUploadResponse

    var _isLoading = MutableLiveData<Boolean>()
    var isLoading: LiveData<Boolean> = _isLoading


    suspend fun uploadImage(imageFile: File){
        val requestImageFile = imageFile.asRequestBody("image/jpeg".toMediaType())
        val multipartBody = MultipartBody.Part.createFormData(
            "photo",
            imageFile.name,
            requestImageFile
        )
        val jsonBody = "{\"profileImg\": \"profile.jpg\"}"

        val client = apiService.uploadImage(multipartBody, jsonBody)
        client.enqueue(object : Callback<UpdateProfilResponse> {
            override fun onResponse(
                call: Call<UpdateProfilResponse>,
                response: Response<UpdateProfilResponse>
            ) {
                if (response.isSuccessful) {
                    _isLoading.value = false
                    uploadResponse.value = response.body()
                }
            }

            override fun onFailure(call: Call<UpdateProfilResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("Repository", "error: ${t.message}")
            }
        })
    }

    fun getHistoryAnswer(id: Int){
        _isLoading.value = true
        val client = apiService.getQuizHistoryDetail(id)
        client.enqueue(object : Callback<UserAnswerResponse> {
            override fun onResponse(
                call: Call<UserAnswerResponse>,
                response: Response<UserAnswerResponse>
            ) {
                if (response.isSuccessful) {
                    _isLoading.value = false
                    getHistoryAnswer.value = response.body()
                }
            }

            override fun onFailure(call: Call<UserAnswerResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("Repository", "error: ${t.message}")
            }
        })
    }
    fun getHistoryQuizData(){
        _isLoading.value = true
        val client = apiService.getQuizHistories()
        client.enqueue(object : Callback<HistoryQuizResponse> {
            override fun onResponse(
                call: Call<HistoryQuizResponse>,
                response: Response<HistoryQuizResponse>
            ) {
                if (response.isSuccessful) {
                    _isLoading.value = false
                    listHistoryQuiz.value = response.body()
                }
            }

            override fun onFailure(call: Call<HistoryQuizResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("Repository", "error: ${t.message}")
            }
        })
    }

    fun getUserData() {
        _isLoading.value = true
        val client = apiService.getUserData()
        client.enqueue(object : Callback<UserResponse> {
            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                if (response.isSuccessful) {
                    _isLoading.value = false
                    userData.value = response.body()
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("Repository", "error: ${t.message}")
            }
        })
    }

    fun getLeaderboardData(){
        _isLoading.value = true
        val client = apiService.getUsersLeaderboard()
        client.enqueue(object : Callback<LeaderboardResponse> {
            override fun onResponse(
                call: Call<LeaderboardResponse>,
                response: Response<LeaderboardResponse>
            ) {
                if (response.isSuccessful) {
                    _isLoading.value = false
                    listLeaderboard.value = response.body()?.data?.leaderboard
                }
            }

            override fun onFailure(call: Call<LeaderboardResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("Repository", "error: ${t.message}")
            }
        })
    }

    fun generateQuizData(tipe: Int?){
        var quizCategory ="Literasi"
        if(tipe==null){
            val rnds = (0..1).random()
        }
        if (tipe == 0){
            quizCategory = "Literasi"
        }else{
            quizCategory = "Skolastik"
        }
        _isLoading.value = true
        val client = apiService.getQuizzes(GetQuizBody(quizCategory))
        client.enqueue(object : Callback<GenerateQuizResponse> {
            override fun onResponse(
                call: Call<GenerateQuizResponse>,
                response: Response<GenerateQuizResponse>
            ) {
                if (response.isSuccessful) {
                    _isLoading.value = false
                    quizId = response.body()?.data?.quizId.toString()
                    listQuiz.value = response.body()
                }
            }

            override fun onFailure(call: Call<GenerateQuizResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("Repository", "error: ${t.message}")
            }
        })
    }
    fun getQuizResult(answerModel: AnswerModel){
        _isLoading.value = true
        val client = apiService.getQuizResult(answerModel)
        client.enqueue(object : Callback<CalculateQuizResponse> {
            override fun onResponse(
                call: Call<CalculateQuizResponse>,
                response: Response<CalculateQuizResponse>
            ) {
                if (response.isSuccessful) {
                    _isLoading.value = false
                    quizResult.value = response.body()
                }
            }

            override fun onFailure(call: Call<CalculateQuizResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("Repository", "error: ${t.message}")
            }
        })
    }

    suspend fun register(name: String, email: String, password: String, schoolOrigin: String): RegisterResponse {
        return apiService.register(RegisterBody(name, email, password, schoolOrigin))
    }

    fun login(email: String, password: String) {
        _isLoading.value = true
//        val client = apiService.login(email, password)
        val client = apiService.login(LoginBody(email, password))
        client.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                if (response.isSuccessful) {
                    _isLoading.value = false
                    loginResult.value = response.body()
//                    getUserData()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e("Repository", "error: ${t.message}")
            }
        })
    }

    suspend fun saveSession(user: UserModel) {
        userPreference.saveSession(user)
    }

    fun getSession(): Flow<UserModel> {
        return userPreference.getSession()
    }

    suspend fun logout() {
        val client = apiService.logout()
        client.enqueue(object : Callback<LogoutResponse> {
            override fun onResponse(
                call: Call<LogoutResponse>,
                response: Response<LogoutResponse>
            ) {
                if (response.isSuccessful) {
                    _isLoading.value = false
                }
            }

            override fun onFailure(call: Call<LogoutResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("Repository", "error: ${t.message}")
            }
        })
        userPreference.logout()
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun clearInstance() {
            instance = null
        }

        fun getInstance(apiService: ApiService, userPreference: UserPreference): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(apiService, userPreference)
            }.also { instance = it }
    }
}