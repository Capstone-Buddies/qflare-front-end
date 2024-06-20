package com.dicoding.capstone.data

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.capstone.data.repository.UserRepository
import com.dicoding.capstone.di.Injection
import com.dicoding.capstone.upload.UploadViewModel
import com.dicoding.capstone.viewmodel.LeaderViewModel
import com.dicoding.capstone.viewmodel.LoginViewModel
import com.dicoding.capstone.viewmodel.MainViewModel
import com.dicoding.capstone.viewmodel.QuizViewModel
import com.dicoding.capstone.viewmodel.RegisterViewModel

class ViewModelFactory(private val repository: UserRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                RegisterViewModel(repository) as T
            }
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(repository) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(repository) as T

            }
            modelClass.isAssignableFrom(LeaderViewModel::class.java) -> {
                LeaderViewModel(repository) as T

            }
            modelClass.isAssignableFrom(QuizViewModel::class.java) -> {
                QuizViewModel(repository) as T
            }
            modelClass.isAssignableFrom(UploadViewModel::class.java) -> {
                UploadViewModel(repository) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun clearInstance() {
            UserRepository.clearInstance()
            INSTANCE = null
        }

        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory(Injection.provideRepository(context))
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }
}