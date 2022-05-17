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
import com.uzlahalya.beosis4.databinding.ActivityResetBinding

class ResetActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var resetBinding: ActivityResetBinding

    companion object {
        fun getService(from: Context) = Intent(from, ResetActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        resetBinding = ActivityResetBinding.inflate(layoutInflater)
        setContentView(resetBinding.root)
        supportActionBar?.hide()
        mAuth = FirebaseAuth.getInstance()

        resetBinding.ivBackReset.setOnClickListener(this)
        resetBinding.btnSendEmail.setOnClickListener(this)
    }

    override fun onClick(p0: View) {
        when (p0.id) {
            R.id.btn_send_email -> ResetPassword()
            R.id.iv_back_reset -> startActivity(SigninActivity.getService(this))
        }
    }

    private fun ResetPassword() {
        val email = resetBinding.etEmailReset.text.toString()
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