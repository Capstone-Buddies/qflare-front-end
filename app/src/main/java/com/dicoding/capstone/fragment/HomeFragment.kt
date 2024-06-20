package com.dicoding.capstone.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.dicoding.Capstone.R
import com.dicoding.capstone.activity.HistoryDetailActivity
import com.dicoding.capstone.activity.LeaderActivity
import com.dicoding.capstone.activity.LoginActivity
import com.dicoding.capstone.activity.QuizActivity
import com.dicoding.capstone.viewmodel.MainViewModel

class HomeFragment : Fragment(), View.OnClickListener {
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate((R.layout.fragment_home), container, false)
        val btnMulai: Button = view.findViewById(R.id.bt_mulai)
        val btnLiterasi: ImageButton = view.findViewById(R.id.ib_literasi)
        val btnSkolastik: ImageButton = view.findViewById(R.id.ib_skolastik)
        val btnLeaderboard: ImageButton = view.findViewById(R.id.ib_leaderboard)

        setupView(view)
        btnMulai.setOnClickListener(this)
        btnLiterasi.setOnClickListener(this)
        btnSkolastik.setOnClickListener(this)
        btnLeaderboard.setOnClickListener(this)
        return view
    }

    private fun setupView(view: View) {
        val usernameText: TextView = view.findViewById(R.id.tv_player)
        val expText: TextView = view.findViewById(R.id.tv_exp)
        val lvlText: TextView = view.findViewById(R.id.level)
        val profileImage: ImageView = view.findViewById(R.id.iv_player)
        viewModel.getUserData().observe(viewLifecycleOwner){user ->
            if(user?.status =="success"){
                viewModel.getLastQuiz().observe(viewLifecycleOwner){data ->
                    if(data.status == "success"){
                        val quizterakhir: TextView = view.findViewById(R.id.quizterakhir)
                        val title_skor: TextView = view.findViewById(R.id.title_skor)
                        val lvl_lastQuiz: TextView = view.findViewById(R.id.lvl_lastQuiz)
                        quizterakhir.text = "Quiz ${data.data.histories[0].quizCategory}"
                        title_skor.text = "Skor ${data.data.histories[0].grade}"
                        lvl_lastQuiz.text = "Level ${data.data.histories[0].level}"
                        val btnHasil: Button = view.findViewById(R.id.button_hasil)
                        btnHasil.setOnClickListener{
                            Log.d("button", "Ke halaman history")
                            val moveIntent = Intent(activity, HistoryDetailActivity::class.java)
                            moveIntent.putExtra("idQuiz", data.data.histories[0].id.toInt())
                            activity?.startActivity(moveIntent)
                        }
                    }
                }
                usernameText.text = user?.data?.username
                expText.text = "EXP: ${user?.data?.exp}"
                lvlText.text = "Level: ${user?.data?.level}"
                if(user?.data?.profileImgUrl != null && user?.data?.profileImgUrl != "null"){
                    Glide.with(this)
                        .load("${user?.data?.profileImgUrl}") // Replace with your image URL
                        .into(profileImage)
                }else{
                    Glide.with(this)
                        .load("@drawable/iv_profile") // Replace with your image URL
                        .into(profileImage)
                }

            }else{
                val moveIntent = Intent(activity, LoginActivity::class.java)
                activity?.startActivity(moveIntent)
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.bt_mulai -> {
                Log.d("button", "Ke halaman quiz")
                val moveIntent = Intent(activity, QuizActivity::class.java)
                moveIntent.putExtra("Kategori", 3)
                activity?.startActivity(moveIntent)
            }

            R.id.ib_literasi -> {
                Log.d("button", "Ke halaman quiz")
                val moveIntent = Intent(activity, QuizActivity::class.java)
                moveIntent.putExtra("Kategori", 0)
                activity?.startActivity(moveIntent)
            }
            R.id.ib_skolastik -> {
                Log.d("button", "Ke halaman quiz")
                val moveIntent = Intent(activity, QuizActivity::class.java)
                moveIntent.putExtra("Kategori", 1)
                activity?.startActivity(moveIntent)
            }
            R.id.ib_leaderboard -> {
                Log.d("button", "Ke halaman leaderboard")
                val moveIntent = Intent(activity, LeaderActivity::class.java)
                activity?.startActivity(moveIntent)
            }
//            R.id.button_hasil -> {
//                Log.d("button", "Ke halaman history")
//                val moveIntent = Intent(activity, HistoryDetailActivity::class.java)
//                activity?.startActivity(moveIntent)
//            }
        }

    }
}