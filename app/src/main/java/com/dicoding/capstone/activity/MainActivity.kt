package com.dicoding.capstone.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.dicoding.Capstone.R
import com.dicoding.capstone.data.ViewModelFactory
import com.dicoding.capstone.viewmodel.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Check if this is the first run
        val sharedPreferences: SharedPreferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
        val isFirstRun: Boolean = sharedPreferences.getBoolean("isFirstRun", true)
        //buat ngetes login register
        if (isFirstRun) {
            // Show onboarding activity
            startActivity(Intent(this, OnboardingActivity::class.java))
            sharedPreferences.edit().putBoolean("isFirstRun", false).apply()
            finish() // close MainActivity
            return
        }
        getSession()
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController =  findNavController(R.id.nav_host_fragment)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_historylist,
                R.id.navigation_profile
            )
        )

//        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private fun getSession() {
        viewModel.getSession().observe(this) { user ->
            if (!user.isLogin) {
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()
            } else {
                viewModel.getUserData()
            }
        }
    }


}
