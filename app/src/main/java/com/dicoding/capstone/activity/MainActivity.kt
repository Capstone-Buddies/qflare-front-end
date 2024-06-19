package com.dicoding.capstone.view

import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.dicoding.Capstone.R
import com.dicoding.capstone.fragment.HistoryListFragment
import com.dicoding.capstone.fragment.HomeFragment
import com.dicoding.capstone.fragment.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController =  findNavController(R.id.nav_host_fragment)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_historylist,
                R.id.navigation_profile

            )
        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }


}
//
//    val TAG ="MainActivity"
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_main)
//
//        val HomeFragment = HomeFragment()
//        val ProfileFragment = ProfileFragment()
//        val HistoryListFragment = HistoryListFragment()
//
//        setCurrentFragment(HomeFragment)
//
//        bottom_
//
////            .setOnNavigationItemSelectedListener {
////            when(it.itemId) {
////                R.id.nav_home -> {
////                    setCurrentFragment(HomeFragment)
////                    Log.i(TAG, "Home Selected")
////                }
////                R.id.nav_box -> {
////                    setCurrentFragment(HistoryListFragment)
////                    Log.i(TAG, "History Selected")
////                }
////                R.id.nav_profile -> {
////                    setCurrentFragment(ProfileFragment)
////                    Log.i(TAG, "Profile Selected")
////                }
////            }
////            true
////        }
//    }
//
//    private fun setCurrentFragment(fragment: Fragment) =
//        supportFragmentManager.beginTransaction().apply {
//            replace(R.id.fl_wrapper, fragment)
//            commit()
//        }
