package com.dicoding.capstone.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.lifecycleScope
import com.dicoding.Capstone.R
import com.dicoding.capstone.data.ViewModelFactory
import com.dicoding.capstone.data.model.RegisterResponse
import com.dicoding.capstone.viewmodel.RegisterViewModel
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class RegisterActivity : AppCompatActivity() {
    private val viewModel by viewModels<RegisterViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrasi)

        // Assuming you have a button to complete registration
        val registerButton: AppCompatButton = findViewById(R.id.button_registrasi)
        val usernameField: EditText = findViewById(R.id.input_nama_lengkap)
        val emailField: EditText = findViewById(R.id.input_email)
        val passwordField: EditText = findViewById(R.id.input_password)
        val konfirmPassField: EditText = findViewById(R.id.input_konfirmasi_sandi)
        val sekolahField: EditText = findViewById(R.id.input_sekolah)
        registerButton.setOnClickListener {
            // Complete registration logic here

            showLoading(true)
            val name = usernameField.text.toString()
            val email = emailField.text.toString()
            val password = passwordField.text.toString()
            val konfirm = konfirmPassField.text.toString()
            val sekolah = sekolahField.text.toString()

            if (name.isEmpty()) {
                showLoading(false)
                usernameField.error = "Field Username kosong"
            } else if (email.isEmpty()) {
                showLoading(false)
                emailField.error = "Field Email kosong"
            } else if (password.isEmpty()) {
                showLoading(false)
                passwordField.error = "Field Password kosong"
            } else if (konfirm.isEmpty()) {
                showLoading(false)
                konfirmPassField.error = "Field Konfirmasi Password kosong"
            } else if (sekolah.isEmpty()) {
                showLoading(false)
                sekolahField.error = "Field Sekolah kosong"
            } else if (konfirm!=password) {
                showLoading(false)
                konfirmPassField.error = "Password tidak sama"
            }

            lifecycleScope.launch {
                try {
                    val response = viewModel.register(name, email, password, sekolah)
                    showLoading(false)
                    showToast(response.message)
                    AlertDialog.Builder(this@RegisterActivity).apply {
                        setTitle("Berhasil!")
                        setMessage("Register Berhasil")
                        setPositiveButton("Next") { _, _ ->
                            val intent = Intent(context, LoginActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                            startActivity(intent)
                            finish()
                        }
                        create()
                        show()
                    }
                } catch (e: HttpException) {
                    showLoading(false)
                    val errorBody = e.response()?.errorBody()?.string()
                    val errorResponse = Gson().fromJson(errorBody, RegisterResponse::class.java)
                    showToast(errorResponse.message)
                    Log.e("Login Error", "Testing Login Error $errorBody")
                }
            }

            // Navigate to MainActivity after registration is complete
//            startActivity(Intent(this, MainActivity::class.java))
//            finish()
        }
        val loginButton: TextView = findViewById(R.id.link_button_login)
        loginButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    private fun showLoading(isLoading: Boolean) {
//        binding.progressLogin.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}