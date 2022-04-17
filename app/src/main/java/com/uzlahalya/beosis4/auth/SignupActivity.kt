package com.uzlahalya.beosis4.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.uzlahalya.beosis4.R
import com.uzlahalya.beosis4.activity.MainActivity
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var refUsers: DatabaseReference
    private var firebaseUserId: String = " "

    companion object {
        fun getService(from: Context) = Intent(from, SignupActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        supportActionBar?.hide ()
        mAuth = FirebaseAuth.getInstance()

        tv_signin_btn_signup.setOnClickListener(this)
        btn_signup.setOnClickListener(this)
    }

    override fun onClick(p0: View) {
        when(p0.id){
            R.id.tv_signin_btn_signup -> startActivity(SigninActivity.getService(this))
            R.id.btn_signup ->Signup()
        }
    }

    private fun Signup() {
        val userName : String = et_name_signup.text.toString()
        val email : String = et_email_signup.text.toString()
        val password : String = et_password_signup.text.toString()
        if(userName == ""){
            Toast.makeText(this, getString(R.string.txt_error), Toast.LENGTH_SHORT).show()
        }else if(email == ""){
            Toast.makeText(this, getString(R.string.txt_error), Toast.LENGTH_SHORT).show()
        }else if(password == ""){
            Toast.makeText(this, getString(R.string.txt_error), Toast.LENGTH_SHORT).show()
        }else{
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { it ->
                if(it.isSuccessful){
                    firebaseUserId = mAuth.currentUser!!.uid
                    refUsers = FirebaseDatabase.getInstance().reference.child(getString(
                        R.string.txt_user
                    )).child(firebaseUserId)
                    val userHashMap = HashMap <String, Any>()

                    userHashMap["uid"] = firebaseUserId
                    userHashMap["userName"] = userName
                    userHashMap["email"] = email

                    refUsers.updateChildren(userHashMap).addOnCompleteListener {
                        if(it.isSuccessful){
                            startActivity(Intent(MainActivity.getLaunchService(this)))
                            finish()
                        }
                    }
                }else{
                    Toast.makeText(this, getString(R.string.txt_error_register)+ it.exception!!
                        .message.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}