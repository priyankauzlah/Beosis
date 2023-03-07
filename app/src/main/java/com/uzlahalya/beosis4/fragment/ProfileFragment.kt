package com.uzlahalya.beosis4.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.uzlahalya.beosis4.R
import com.uzlahalya.beosis4.activity.OnboardingActivity
import com.uzlahalya.beosis4.adapter.ScholarshipAdapter
import com.uzlahalya.beosis4.databinding.FragmentProfileBinding
import com.uzlahalya.beosis4.util.observeLiveData
import com.uzlahalya.beosis4.viewmodel.MainViewModel

class ProfileFragment : Fragment(), View.OnClickListener {

    var firebaseUser: FirebaseUser? = null
    private lateinit var profileFragmentProfileBinding: FragmentProfileBinding

    private val scholarAdapter by lazy {
        ScholarshipAdapter(requireContext())
    }

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseUser = FirebaseAuth.getInstance().currentUser
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        profileFragmentProfileBinding = FragmentProfileBinding.inflate(layoutInflater)

        mainViewModel.fetchSavedScholarships()

        mainViewModel.savedScholarshipList.observeLiveData(
            owner =  viewLifecycleOwner,
            context = requireContext(),
            onSuccess = {
                scholarAdapter.setData(it)
            }
        )

        val lmExploreMore = LinearLayoutManager(activity)
        lmExploreMore.orientation = LinearLayoutManager.VERTICAL

        profileFragmentProfileBinding.apply {
            rvSave.apply {
                setHasFixedSize(true)
                layoutManager = lmExploreMore
                adapter = scholarAdapter
            }
        }
        return profileFragmentProfileBinding.root
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.fetchSavedScholarships()
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