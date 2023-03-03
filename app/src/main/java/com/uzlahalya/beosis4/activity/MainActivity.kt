package com.uzlahalya.beosis4.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.uzlahalya.beosis4.R
import com.uzlahalya.beosis4.databinding.ActivityMainBinding
import com.uzlahalya.beosis4.fragment.*

class MainActivity : AppCompatActivity() {

    private lateinit var currentFragment : Fragment
    private lateinit var mainBinding: ActivityMainBinding

    companion object{
        fun getLaunchService (from: Context) = Intent(from, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        supportActionBar?.hide()

        supportFragmentManager.beginTransaction().replace(R.id.main_container, HomeFragment()).commit()
        val bottomNav : BottomNavigationView = findViewById(R.id.bottom_navigation_view)
        bottomNav.setOnNavigationItemSelectedListener(navListener)
    }

    val navListener = BottomNavigationView.OnNavigationItemSelectedListener {
        when(it.itemId){
            R.id.articleFragment -> {
                currentFragment = ArticleFragment()
            }
            R.id.homeFragment -> {
                currentFragment = HomeFragment()
            }
            R.id.profileFragment -> {
                currentFragment = ProfileFragment()
            }
        }
        supportFragmentManager.beginTransaction().replace(R.id.main_container, currentFragment).commit()
        true
    }
}