package com.uzlahalya.beosis4.auth

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.uzlahalya.beosis4.R
import com.uzlahalya.beosis4.activity.MainActivity
import kotlinx.android.synthetic.main.activity_signin.*

class SigninActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var firebaseAuth: FirebaseAuth

    companion object {
        fun getService(from: Context) = Intent(from, SigninActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        supportActionBar?.hide()
        firebaseAuth = FirebaseAuth.getInstance()

        tv_reset_pass.setOnClickListener(this)
        tv_signup_btn_sigin.setOnClickListener(this)
        btn_signin.setOnClickListener(this)
    }

    override fun onClick(p0: View) {
        when (p0.id) {
            R.id.tv_reset_pass -> startActivity(ResetActivity.getService(this))
            R.id.tv_signup_btn_sigin -> startActivity(SignupActivity.getService(this))
            R.id.btn_signin -> Signin()
        }
    }

    private fun Signin() {
        val email = et_email_signin.text.toString()
        val password = et_password_signin.text.toString()
        if (email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Email or Password cannot be empty", Toast.LENGTH_SHORT).show()
        }

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener{
            if (it.isSuccessful){
                Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
                startActivity(MainActivity.getLaunchService(this))
                return@addOnCompleteListener
            }else{
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            val progress = ProgressDialog(this, R.style.Theme_AppCompat_Light_Dialog)
            progress.hide()
            Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
        }
    }
}