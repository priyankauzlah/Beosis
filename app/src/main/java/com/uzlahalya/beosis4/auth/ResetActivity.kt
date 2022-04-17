package com.uzlahalya.beosis4.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.uzlahalya.beosis4.R
import kotlinx.android.synthetic.main.activity_reset.*

class ResetActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mAuth: FirebaseAuth

    companion object {
        fun getService(from: Context) = Intent(from, ResetActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset)
        supportActionBar?.hide()
        mAuth = FirebaseAuth.getInstance()

        iv_back_reset.setOnClickListener(this)
        btn_send_email.setOnClickListener(this)
    }

    override fun onClick(p0: View) {
        when (p0.id) {
            R.id.btn_send_email -> ResetPassword()
            R.id.iv_back_reset -> startActivity(SigninActivity.getService(this))
        }
    }

    private fun ResetPassword() {
        val email = et_email_reset.text.toString()
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, getString(R.string.txt_error), Toast.LENGTH_SHORT).show()
        } else {
            mAuth.sendPasswordResetEmail(email).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "We have sent a link to your email", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(this, "Make sure your email is correct", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}