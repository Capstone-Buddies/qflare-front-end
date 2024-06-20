package com.dicoding.capstone.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.lifecycleScope
import com.dicoding.Capstone.R
import com.dicoding.capstone.data.ViewModelFactory
import com.dicoding.capstone.data.model.LoginResponse
import com.dicoding.capstone.data.pref.UserModel
import com.dicoding.capstone.viewmodel.LoginViewModel
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginActivity : AppCompatActivity() {
    private val viewModel by viewModels<LoginViewModel> {
        ViewModelFactory.getInstance(this)
    }
//    private var _binding: ActivityLoginBinding? = null
//    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Assuming you have a button to complete Login
        val loginButton: AppCompatButton = findViewById(R.id.button_login)
        val inputEmail: EditText = findViewById(R.id.input_email)
        val inputPassword: EditText = findViewById(R.id.input_password)
        val registerButton: TextView = findViewById(R.id.link_button_registrasi)
        registerButton.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
        loginButton.setOnClickListener {
            // Complete Login logic here

            try {
                viewModel.isLoading.observe(this) {
                    showLoading(it)
                }

                val email = inputEmail.text.toString()
                val password = inputPassword.text.toString()

                if (email.isEmpty()) {
                    inputEmail.error = "Email Kosong!"
                } else if (password.isEmpty()) {
                    inputPassword.error = "Password Kosong!"
                }


                viewModel.login(email, password)
                viewModel.loginResult.observe(this) {
                    Log.e("Login Sukses", "it: ${it}")
                    if (it.status == "success") {
                        save(
                            UserModel(
                                it.data?.token.toString(),
                                "",
                                it.data?.email.toString(),
                                "",
                                "",
                                "",
                                "",
                                true
                            )
                        )
                    }
                }
            } catch (e: HttpException) {
                showLoading(false)
                val errorBody = e.response()?.errorBody()?.string()
                val errorResponse = Gson().fromJson(errorBody, LoginResponse::class.java)
                showToast(errorResponse.message)
                Log.e("Login Error", "Testing Login Error $errorBody")

            }

            // Navigate to MainActivity after Login is complete
        }
    }

    private fun showLoading(isLoading: Boolean) {
//        binding.progressLogin.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun save(session: UserModel) {
        lifecycleScope.launch {
            viewModel.saveSession(session)
            ViewModelFactory.clearInstance()
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}