package com.dicoding.capstone.upload


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.capstone.data.model.UpdateProfilResponse
import com.dicoding.capstone.data.repository.UserRepository
import java.io.File

class UploadViewModel (private val repository: UserRepository) : ViewModel() {
//    suspend fun uploadImage(file: File) = repository.uploadImage(file)
    suspend fun uploadImage(file: File): LiveData<UpdateProfilResponse> {
        repository.uploadImage(file)
        return repository.uploadResponse
    }
}