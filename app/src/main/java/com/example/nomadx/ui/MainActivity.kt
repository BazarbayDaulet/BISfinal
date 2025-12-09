package com.example.nomadx.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.nomadx.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) loadFragment(FeedFragment())

        findViewById<BottomNavigationView>(R.id.bottomNav).setOnItemSelectedListener {
            when(it.itemId) {
                R.id.nav_feed -> loadFragment(FeedFragment())
                R.id.nav_add -> loadFragment(AddPostFragment())
                R.id.nav_profile -> loadFragment(ProfileFragment())
            }
            true
        }
    }

    private fun loadFragment(f: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, f).commit()
    }
}