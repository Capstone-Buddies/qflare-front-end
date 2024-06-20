package com.dicoding.capstone.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.dicoding.Capstone.databinding.FragmentLeaderboardBinding
import com.dicoding.capstone.adapter.LeaderboardAdapter
import com.dicoding.capstone.data.ViewModelFactory
import com.dicoding.capstone.viewmodel.LeaderViewModel

class LeaderActivity : AppCompatActivity() {
    private val viewModel by viewModels<LeaderViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: FragmentLeaderboardBinding
    private lateinit var adapter: LeaderboardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentLeaderboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the RecyclerView and set an empty adapter
        adapter = LeaderboardAdapter()
        binding.rvleaderboard.layoutManager = LinearLayoutManager(this)
        binding.rvleaderboard.adapter = adapter
        binding.backButton.setOnClickListener { super.onBackPressed() }

        // Fetch data and update the adapter
        getLeaderData()
    }

    private fun getLeaderData() {
        viewModel.getLeaderboardData().observe(this){ listData ->
            val textViews = arrayOf(binding.tvRanking1, binding.tvRanking2, binding.tvRanking3)
            val expViews = arrayOf(binding.tvExp1, binding.tvExp2, binding.tvExp3)
            val imageViews = arrayOf(binding.ivRank1, binding.ivRank2, binding.ivRank3)
            var data = listData.sortedByDescending { it.exp }
            for (i in 0 until 3) {
                textViews[i].text = data[i].username
                expViews[i].text = "${data[i].exp} EXP"
                if(data[i].profileImgUrl != null && data[i].profileImgUrl != "null"){
                    Glide.with(this)
                        .load("${data[i].profileImgUrl}") // Replace with your image URL
                        .into(imageViews[i])
                }else{
                    Glide.with(this)
                        .load("@drawable/iv_profile") // Replace with your image URL
                        .into(imageViews[i])
                }
            }
            var dataOther = data.subList(3, data.size)
            adapter.submitList(dataOther)
        }
    }
}