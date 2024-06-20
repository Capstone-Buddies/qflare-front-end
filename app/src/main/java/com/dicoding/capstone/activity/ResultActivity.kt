package com.dicoding.capstone.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.Capstone.R
import com.dicoding.Capstone.databinding.FragmentResultBinding

class ResultActivity :AppCompatActivity() {
    private lateinit var binding: FragmentResultBinding

    //    val newExp = intent.getStringExtra("newExp")
//    val newLevel = intent.getStringExtra("newLevel")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.fragment_result)
        binding = FragmentResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val grade = intent.getIntExtra("grade", 0)
        val expGain = intent.getIntExtra("expGain", 0)
        Log.d("answerResult", "Grade ${grade}, expGain ${expGain}")
        binding.expText.text = "+${expGain} EXP"
        binding.scoreNumber.text = "$grade"
        val backButton: Button = findViewById(R.id.backToMenuButton)
        backButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        val playagainButton: TextView = findViewById(R.id.playAgainButton)
        playagainButton.setOnClickListener {
            startActivity(Intent(this, QuizActivity::class.java))
            finish()
        }

    }
}