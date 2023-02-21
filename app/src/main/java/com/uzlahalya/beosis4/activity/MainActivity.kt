package com.uzlahalya.beosis4.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.uzlahalya.beosis4.R
import com.uzlahalya.beosis4.data.ScholarshipAdapterr
import com.uzlahalya.beosis4.databinding.ActivityMainBinding
import com.uzlahalya.beosis4.fragment.*
import com.uzlahalya.beosis4.model.ScholarshipItem
import com.uzlahalya.beosis4.model.ScholarshipResponse
import com.uzlahalya.beosis4.utils.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        ApiService.getService().getData().enqueue(object : Callback<ScholarshipItem> {
            override fun onResponse(
                call: Call<ScholarshipItem>,
                response: Response<ScholarshipItem>
            ) {
                Log.d("RESPONSE", response.isSuccessful.toString())
                if (response.isSuccessful){
                    val scholarshipItem = response.body()
                    val dataScholarship = scholarshipItem
                    val scholarshipAdapterr = ScholarshipAdapterr()

                }
            }

            override fun onFailure(call: Call<ScholarshipItem>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

        supportFragmentManager.beginTransaction().replace(R.id.main_container, HomeFragment()).commit()
        val bottomNav : BottomNavigationView = findViewById(R.id.bottom_navigation_view)
        bottomNav.setOnNavigationItemSelectedListener(navListener)
    }

    val navListener = BottomNavigationView.OnNavigationItemSelectedListener {
        when(it.itemId){
            R.id.articleFragment -> {
                currentFragment = ArticleFragment()
            }
            R.id.calendarFragment -> {
                currentFragment = CalendarFragment()
            }
            R.id.homeFragment -> {
                currentFragment = HomeFragment()
            }
            R.id.saveFragment -> {
                currentFragment = SaveFragment()
            }
            R.id.profileFragment -> {
                currentFragment = ProfileFragment()
            }
        }
        supportFragmentManager.beginTransaction().replace(R.id.main_container, currentFragment).commit()
        true
    }
}