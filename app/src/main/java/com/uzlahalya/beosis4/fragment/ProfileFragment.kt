package com.uzlahalya.beosis4.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.uzlahalya.beosis4.R
import com.uzlahalya.beosis4.activity.OnboardingActivity
import com.uzlahalya.beosis4.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(), View.OnClickListener {

    var firebaseUser: FirebaseUser? = null
    private lateinit var profileFragmentProfileBinding: FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseUser = FirebaseAuth.getInstance().currentUser
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        profileFragmentProfileBinding = FragmentProfileBinding.inflate(layoutInflater)
        return profileFragmentProfileBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profileFragmentProfileBinding.btnLogout.setOnClickListener(this)
    }

    override fun onClick(p0: View) {
        when (p0.id) {
            R.id.btn_logout -> logOut()
        }
    }

    private fun logOut() {
        val intent = Intent(activity, OnboardingActivity::class.java)
        startActivity(intent)
        FirebaseAuth.getInstance().signOut()
    }
}