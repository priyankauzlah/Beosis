package com.uzlahalya.beosis4.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.uzlahalya.beosis4.R
import com.uzlahalya.beosis4.auth.SigninActivity
import com.uzlahalya.beosis4.auth.SignupActivity
import com.uzlahalya.beosis4.databinding.ActivityOnboardingBinding

class OnboardingActivity : AppCompatActivity() {
    private lateinit var onboardingBinding: ActivityOnboardingBinding

    companion object {
        fun getService(from: Context) = Intent(from, OnboardingActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onboardingBinding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(onboardingBinding.root)
        supportActionBar?.hide()
        screenMove()
    }

    private fun screenMove() {
        onboardingBinding.btnSignin.setOnClickListener {
            startActivity(SigninActivity.getService(this))
        }
        onboardingBinding.btnSignup.setOnClickListener {
            startActivity(SignupActivity.getService(this))
        }
    }
}