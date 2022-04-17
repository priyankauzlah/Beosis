package com.uzlahalya.beosis4.activity

import android.app.PendingIntent.getService
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.uzlahalya.beosis4.R
import com.uzlahalya.beosis4.activity.SplashActivity.Companion.getService
import com.uzlahalya.beosis4.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var splashBinding: ActivitySplashBinding

    companion object {
        fun getService(from: Context) = Intent(from, SplashActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        splashBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(splashBinding.root)
    }

    override fun onResume() {
        object : CountDownTimer(3600, 200) {
            override fun onTick(p0: Long) {
            }

            override fun onFinish() {
                startActivity(OnboardingActivity.getService(this@SplashActivity))
            }
        }.start()
        super.onResume()
    }
}