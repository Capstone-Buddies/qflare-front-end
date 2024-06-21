package com.dicoding.capstone.fragment

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.dicoding.Capstone.R
import com.dicoding.capstone.activity.RegisterActivity
import com.dicoding.capstone.viewmodel.MainViewModel
class ProfileFragment : Fragment(), View.OnClickListener {
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate((R.layout.fragment_profile), container, false)
        val btnLogout: Button = view.findViewById(R.id.logout_button)
        val btnUpload: ImageView = view.findViewById(R.id.camera_icon)
        btnUpload.setOnClickListener{
//            val moveIntent = Intent(activity, UploadActivity::class.java)
//            activity?.startActivity(moveIntent)
        }
        setupView(view)
        btnLogout.setOnClickListener(this)
        return view
    }
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.logout_button -> {
                Log.d("button", "Ke halaman register")
//              tambahkan fungsi log out disini (API Service)
                viewModel.logout()
                val moveIntent = Intent(activity, RegisterActivity::class.java)
                activity?.startActivity(moveIntent)
            }

        }

    }
    private fun setupView(view: View) {
        val usernameText: TextView = view.findViewById(R.id.user_name)
        val expText: TextView = view.findViewById(R.id.exp_text)
        val lvlText: TextView = view.findViewById(R.id.user_level)
        val emailText: TextView = view.findViewById(R.id.user_email)
        val SchoolText: TextView = view.findViewById(R.id.user_school)
        val progressBar: ProgressBar = view.findViewById(R.id.exp_progress)
        val menuProfil: ImageView = view.findViewById(R.id.profile_picture)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            menuProfil.setClipToOutline(true);
        }
        viewModel.getUserData().observe(viewLifecycleOwner){user ->
            usernameText.setText(user?.data?.username)
            expText.setText("${user?.data?.exp}/1000")
            lvlText.setText("${user?.data?.level}")
            emailText.setText("${user?.data?.email}")
            SchoolText.setText("Asal Sekolah: ${user?.data?.schoolOrigin}")
            progressBar.progress = user?.data?.exp!!/10
            progressBar.max = 100
            if(user?.data?.profileImgUrl != null && user?.data?.profileImgUrl != "null"){
                Glide.with(this)
                    .load("${user?.data?.profileImgUrl}") // Replace with your image URL
                    .into(menuProfil)
            }else{
                Glide.with(this)
                    .load("@drawable/iv_profile") // Replace with your image URL
                    .into(menuProfil)
            }
        }
    }


}