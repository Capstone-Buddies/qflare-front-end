package com.dicoding.capstone.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.Capstone.databinding.FragmentQuizBinding
import com.dicoding.capstone.data.model.QuestionsItem

class QuizAdapter(private val quizItems: List<QuestionsItem>) :
    RecyclerView.Adapter<QuizAdapter.QuizViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.quiz_item_layout, parent, false)
        val binding = FragmentQuizBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuizViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        val quizItem = quizItems[position]
        holder.bind(quizItem)
    }

    override fun getItemCount(): Int = quizItems.size

    inner class QuizViewHolder(private val binding: FragmentQuizBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(quizItem: QuestionsItem) {
            itemView.apply {
//                binding.tvQuestionNumber.text = "${quizItem.id}. "
//                binding.tvQuestionText.text = quizItem.question
//                binding.buttonOptionA.text = quizItem.option1
//                binding.buttonOptionB.text = quizItem.option2
//                binding.buttonOptionC.text = quizItem.option3
//                binding.buttonOptionD.text = quizItem.option4
            }
        }
    }
}