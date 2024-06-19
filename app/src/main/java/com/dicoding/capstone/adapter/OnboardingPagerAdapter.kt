package com.dicoding.capstone.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter (fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager){

    private val fragment = listOf(
        Onboarboarding1Fragment(),
        Onboarboarding2Fragment(),
        Onboarboarding3Fragment()

    )

    override fun getItem(position: Int): Fragment {
        return fragments(position)

    }

    override fun getCount(): Int {
        return fragment.size

    }

}