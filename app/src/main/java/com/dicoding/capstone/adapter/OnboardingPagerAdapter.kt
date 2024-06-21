package com.dicoding.capstone.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dicoding.capstone.fragment.OnboardingFragment1
import com.dicoding.capstone.fragment.OnboardingFragment2
import com.dicoding.capstone.fragment.OnboardingFragment3

class OnboardingPagerAdapter(activity: FragmentActivity, private val onComplete: () -> Unit) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> OnboardingFragment1()
            1 -> OnboardingFragment2()
            else -> OnboardingFragment3(onComplete)
        }
    }
}