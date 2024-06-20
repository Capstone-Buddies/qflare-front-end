package com.dicoding.capstone.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.Capstone.databinding.LeaderboardItemLayoutBinding
import com.dicoding.capstone.data.model.LeaderboardItem

class LeaderboardAdapter : RecyclerView.Adapter<LeaderboardAdapter.ViewHolder>() {

    private var leaders: List<LeaderboardItem> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LeaderboardItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val leader = leaders[position]
        holder.bind(leader)
    }

    override fun getItemCount(): Int = leaders.size

    fun submitList(newLeaders: List<LeaderboardItem>) {
        leaders = newLeaders
        notifyDataSetChanged() // Refresh the entire list
    }

    inner class ViewHolder(private val binding: LeaderboardItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(leader: LeaderboardItem) {
            with(binding) {
                nameLeader.text = leader.username
                lvlExpLeader.text = "LVL: ${leader.level} / EXP: ${leader.exp}"
                if(leader.profileImgUrl != null && leader.profileImgUrl != "null"){
                    Glide.with(itemView.context)
                        .load(leader.profileImgUrl)
                        .into(imageLeader)
                }else{
                    Glide.with(itemView.context)
                        .load("https://media.istockphoto.com/id/1337144146/vector/default-avatar-profile-icon-vector.jpg?s=612x612&w=0&k=20&c=BIbFwuv7FxTWvh5S3vB6bkT0Qv8Vn8N5Ffseq84ClGI=") // Replace with your image URL
                        .into(imageLeader)
                }
            }
        }
    }
}