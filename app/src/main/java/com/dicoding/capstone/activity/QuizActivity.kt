package com.dicoding.capstone.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.Capstone.R
import com.dicoding.Capstone.databinding.FragmentQuizBinding
import com.dicoding.capstone.data.ViewModelFactory
import com.dicoding.capstone.data.model.QuestionsItem
import com.dicoding.capstone.data.pref.Answer
import com.dicoding.capstone.data.pref.AnswerModel
import com.dicoding.capstone.viewmodel.QuizViewModel

class QuizActivity :AppCompatActivity(){
    private val viewModel by viewModels<QuizViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: FragmentQuizBinding
    private var currentQuizIndex = 0
    private var quizItems: List<QuestionsItem> = mutableListOf()
    private var quizAnswer: MutableList<Answer> = mutableListOf()
    private var tempAnswer =0
    private var quizId = 0
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable
    private var elapsedTime = 0 // Seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val kategori = intent.getIntExtra("Kategori", 3)
        binding = FragmentQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handler = Handler(Looper.getMainLooper())
        runnable = object : Runnable {
            override fun run() {
                elapsedTime += 1
                handler.postDelayed(this, 1000) // Update every second
            }
        }
        binding.loadingIndicatorQuiz.visibility = View.VISIBLE
//        if (binding.loadingIndicatorQuiz.visibility == View.VISIBLE) {
//            binding.overlayView.visibility = View.VISIBLE
//        } else {
//            binding.overlayView.visibility = View.GONE
//        }
//        showToast(kategori.toString())
        loadQuizItems(kategori)
        binding.buttonNext.setOnClickListener {
            if(tempAnswer!=0){
                quizAnswer.add(currentQuizIndex, Answer(currentQuizIndex+1, tempAnswer, elapsedTime))
                selectClear()
                currentQuizIndex++
                updateQuizUI()
                elapsedTime=0
                tempAnswer=0
            }else{
                showToast("Pilih jawaban terlebih dahulu")
            }
        }
        binding.imageOptionA.setOnClickListener{
            selectOption(binding.imageOptionA)
            tempAnswer=1
        }
        binding.imageOptionB.setOnClickListener{
            selectOption(binding.imageOptionB)
            tempAnswer=2
        }
        binding.imageOptionC.setOnClickListener{
            selectOption(binding.imageOptionC)
            tempAnswer=3
        }
        binding.imageOptionD.setOnClickListener{
            selectOption(binding.imageOptionD)
            tempAnswer=4
        }
    }
    private fun startElapsedTimeCounter() {
        handler.post(runnable)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runnable) // Stop the handler when activity is destroyed
    }
    private fun startTimer(onFinish: () -> Unit) {
        val handler = Handler(Looper.getMainLooper())
        binding.quizTimer.max = 100
        var progressStatus = 100

        Thread {
            while (progressStatus > 0) {
                progressStatus -= 1
                handler.post {
                    binding.quizTimer.progress = progressStatus
                }
                Thread.sleep(15 * 60 * 1000L) // Simulate some work
            }
            handler.post {
                onFinish() // Call the onFinish callback when the timer is done
            }
        }.start()
    }
    private fun onTimerFinished() {
        // This function will be called when the timer finishes
        showToast("Waktu Habis ${elapsedTime}")
        if (currentQuizIndex < quizItems.size) {
            for (i in currentQuizIndex+1..quizItems.size) {
                quizAnswer.add(i-1, Answer(i, 0, 0))
                println(quizAnswer[i-1])
            }
        }
        submitAnswer()
        // Add any other actions you want to perform here
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun selectOption(option: ImageView){
        selectClear()
        when (option?.id) {
            binding.imageOptionA.id -> {
                binding.imageOptionA.setImageResource(R.drawable.button_a_selected)
            }
            binding.imageOptionB.id -> {
                binding.imageOptionB.setImageResource(R.drawable.button_b_selected)
            }
            binding.imageOptionC.id -> {
                binding.imageOptionC.setImageResource(R.drawable.button_c_selected)
            }
            binding.imageOptionD.id -> {
                binding.imageOptionD.setImageResource(R.drawable.button_d_selected)
            }
        }
    }
    private fun selectClear(){
        binding.imageOptionA.setImageResource(R.drawable.button_a)
        binding.imageOptionB.setImageResource(R.drawable.button_b)
        binding.imageOptionC.setImageResource(R.drawable.button_c)
        binding.imageOptionD.setImageResource(R.drawable.button_d)
    }

    private fun loadQuizItems(tipe: Int?) {
        // Replace this with your logic to load quiz items from a data source
        // For demonstration, adding dummy data
        viewModel.generateQuiz(tipe).observe(this){ listData ->
            if(listData.status == "success"){
                startTimer{
                    onTimerFinished()
                }
                startElapsedTimeCounter()
                quizItems = listData.data.questions
                updateQuizUI()
                quizId = viewModel.getQuizId().toInt()
                binding.loadingIndicatorQuiz.visibility = View.INVISIBLE
                binding.overlayView.visibility = View.GONE
            }else if(listData.status == "fail"){
                showToast(listData.message)
                val moveIntent = Intent(this@QuizActivity, MainActivity::class.java)
                startActivity(moveIntent)
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
        } else {
            // Handle end of quiz
            // For example, show a message or navigate to another screen
            submitAnswer()
            Log.d("Data Quiz", "$quizAnswer")
        }
    }
    private fun submitAnswer(){
        viewModel.submitAnswer(AnswerModel(quizId, quizAnswer)).observe(this){ listData ->
            if(listData.status == "success"){
                var data = listData.data
                Log.d("answerResult", "Grade ${data.grade}, expGain ${data.expGain}, newExp ${data.newExp}, newLevel ${data.newLevel}, ")
                val moveIntent = Intent(this@QuizActivity, ResultActivity::class.java)
                moveIntent.putExtra("grade", data.grade)
                moveIntent.putExtra("expGain", data.expGain)
                moveIntent.putExtra("newExp", data.newExp)
                moveIntent.putExtra("newLevel", data.newLevel)
                startActivity(moveIntent)
            }else if(listData.status == "fail"){
                showToast("Error when grading. ${listData.message}")
                val moveIntent = Intent(this@QuizActivity, MainActivity::class.java)
                startActivity(moveIntent)
            }
        }
    }
}