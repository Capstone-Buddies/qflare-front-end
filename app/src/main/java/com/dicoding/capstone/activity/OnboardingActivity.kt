package com.dicoding.capstone.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.dicoding.Capstone.R
import com.dicoding.capstone.adapter.OnboardingPagerAdapter

class OnboardingActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        viewPager = findViewById(R.id.viewPager)
        val adapter = OnboardingPagerAdapter(this) { onCompleteOnboarding() }
        viewPager.adapter = adapter
    }

    private fun onCompleteOnboarding() {
        startActivity(Intent(this, RegisterActivity::class.java))
        finish()
    }
}