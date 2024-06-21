package com.dicoding.capstone.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.dicoding.Capstone.R

class OnboardingFragment3(private val onComplete: () -> Unit) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_onboarding3, container, false)
        val completeButton: ImageButton = view.findViewById(R.id.nextButton)
        completeButton.setOnClickListener {
            onComplete()
        }
        return view
    }
}