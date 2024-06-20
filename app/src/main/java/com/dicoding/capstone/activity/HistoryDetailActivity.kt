package com.dicoding.capstone.activity

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.Capstone.databinding.FragmentHistoryDetailBinding
import com.dicoding.capstone.data.ViewModelFactory
import com.dicoding.capstone.data.model.AnswersItem
import com.dicoding.capstone.viewmodel.QuizViewModel

class HistoryDetailActivity :AppCompatActivity() {
    private val viewModel by viewModels<QuizViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private var currentQuizIndex = 0
    private var quizItems: List<AnswersItem> = mutableListOf()
    private lateinit var binding: FragmentHistoryDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentHistoryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.loadingIndicatorQuiz.visibility = View.VISIBLE
        binding.overlayView.visibility = View.VISIBLE
        val id = intent.getIntExtra("idQuiz", 0)
        loadQuizItems(id)
//        val backBtn: ImageButton = findViewById(R.id.button_back)
        binding.buttonBack.setOnClickListener {
            finish()
        }
        binding.buttonNext.setOnClickListener {
            if(quizItems.size>currentQuizIndex){
                currentQuizIndex++
                updateQuizUI()
                binding.buttonPrevious.visibility = View.VISIBLE
                if(currentQuizIndex == quizItems.size-1){
                    binding.buttonNext.visibility = View.INVISIBLE
                }
            }
        }
        binding.buttonPrevious.setOnClickListener {
            if(0<currentQuizIndex){
                currentQuizIndex--
                updateQuizUI()
                binding.buttonNext.visibility = View.VISIBLE
                if(currentQuizIndex==0){
                    binding.buttonPrevious.visibility = View.INVISIBLE
                }
            }
        }
    }

    private fun loadQuizItems(id: Int) {
        // Replace this with your logic to load quiz items from a data source
        // For demonstration, adding dummy data
        viewModel.getAnswerHistory(id).observe(this){ listData ->
            if(listData.status == "success"){
                quizItems = listData.data.answers
                updateQuizUI()
                binding.loadingIndicatorQuiz.visibility = View.INVISIBLE
                binding.overlayView.visibility = View.GONE
            }else{
                finish()
            }
        }
        // Add more items as needed
    }

    private fun updateQuizUI() {
        if (currentQuizIndex < quizItems.size) {
            val currentQuizItem = quizItems[currentQuizIndex]
            binding.tvQuestionNumber.text = "${currentQuizIndex+1}. "
            binding.tvQuestionText.text = currentQuizItem.question
            binding.buttonOptionA.text = currentQuizItem.option1
            binding.buttonOptionB.text = currentQuizItem.option2
            binding.buttonOptionC.text = currentQuizItem.option3
            binding.buttonOptionD.text = currentQuizItem.option4
            when (currentQuizItem.correctness) {
                true -> binding.tvAnswer.text = "Jawaban Benar"
                false -> binding.tvAnswer.text = "Jawaban Salah"
                // Add more conditions here if needed
            }
            val listAnswer= arrayOf("A. ${currentQuizItem.option1}", "B. ${currentQuizItem.option2}", "C. ${currentQuizItem.option3}", "D. ${currentQuizItem.option4}")
            val index = currentQuizItem.userAnswer-1
            if(index > 0){
                binding.tvTheAnswer.text = listAnswer[currentQuizItem.userAnswer-1]
            }else{
                binding.tvTheAnswer.text = "API ERROR"
            }
            binding.tvDate.text = "${currentQuizItem.duration} Detik"


        }
    }
}