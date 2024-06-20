package com.dicoding.capstone.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.Capstone.databinding.HistoryItemLayoutBinding
import com.dicoding.capstone.activity.HistoryDetailActivity
import com.dicoding.capstone.data.model.HistoriesItem
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

class HistoryListAdapter() :
    RecyclerView.Adapter<HistoryListAdapter.HistoryViewHolder>() {
    private var histories: List<HistoriesItem> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = HistoryItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(histories[position])
    }
    fun submitList(newHistories: List<HistoriesItem>) {
        histories = newHistories
        notifyDataSetChanged() // Refresh the entire list
    }

    override fun getItemCount(): Int = histories.size

    class HistoryViewHolder(private val binding: HistoryItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(history: HistoriesItem) {
            binding.tvLevel.text = "Level: ${history.level}"
            binding.tvGrade.text = "Skor ${history.grade}"
            binding.tvQuizCategory.text = "Quiz ${history.quizCategory}"
            binding.tvTimestamp.text = convertDateString(history.timestamp)
            binding.btHistory.setOnClickListener{
                val context = binding.root.context
                val intent = Intent(context, HistoryDetailActivity::class.java) // Replace DetailActivity with your target activity
                intent.putExtra("idQuiz", history.id.toInt())
                context.startActivity(intent)
            }
        }
        fun convertDateString(inputDate: String): String? {
            return try {
                // Define the input date format
                val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
                inputFormat.timeZone = TimeZone.getTimeZone("UTC")

                // Parse the input date string to a Date object
                val date = inputFormat.parse(inputDate)

                // Define the output date format
                val outputFormat = SimpleDateFormat("yyyy/MM/dd - HH:mm", Locale.getDefault())
                outputFormat.timeZone = TimeZone.getDefault()

                // Format the Date object to the desired output string
                date?.let { outputFormat.format(it) }
            } catch (e: Exception) {
                // Handle the exception
                e.printStackTrace()
                null
            }
        }
    }

}