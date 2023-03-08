package com.uzlahalya.beosis4.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.uzlahalya.beosis4.R
import com.uzlahalya.beosis4.data.Scholarship
import com.uzlahalya.beosis4.databinding.ActivityDetailScholarshipBinding
import com.uzlahalya.beosis4.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_detail_scholarship.*

class DetailScholarshipActivity : AppCompatActivity() {

    private lateinit var detailScholarshipBinding: ActivityDetailScholarshipBinding
    private var dataScholar: Scholarship? = null

    private val mainViewModel by viewModels<MainViewModel>()

    private var isFavorite: Boolean = false

    companion object {
        const val EXTRA_SCHOLARSHIP = "extra_scholarship"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailScholarshipBinding = ActivityDetailScholarshipBinding.inflate(layoutInflater)
        setContentView(detailScholarshipBinding.root)
        supportActionBar?.hide()
        dataScholar = intent.getParcelableExtra(EXTRA_SCHOLARSHIP)

        dataScholar?.let {
            mainViewModel.checkScholarshipIsFavorited(it.id)
        }

        mainViewModel.isFavorite.observe(this) {
            isFavorite = it
            setSavedState(isFavorite)
        }

        ib_arrow_back_detail_scholarship.setOnClickListener {
            finish()
        }

        ib_saved_detailscholarship.setOnClickListener {
            dataScholar?.let {
                if (isFavorite){mainViewModel.deleteScholarship(it)
                    Toast.makeText(this, "Delete from save", Toast.LENGTH_SHORT).show()
                }
                else{mainViewModel.insertScholarship(it)
                    Toast.makeText(this, "Added to save", Toast.LENGTH_SHORT).show()
                }
            }
        }

        detailScholarshipBinding.apply {
            Glide.with(applicationContext).load(dataScholar?.logo).into(ivImageDetailscholarship);
            tvTitleScholarshipDetailscholarship.text = dataScholar?.name
            tvUniDetailscholarship.text = dataScholar?.university
            tvCountryDetailscholarship.text = dataScholar?.country
            tvMajorDetailscholarship.text = dataScholar?.majors
            tvDegreeDetailScholarship.text = dataScholar?.degree
            tvDateOpenregistrationDetailscholarship.text = dataScholar?.openregistration
            tvDateDeadlineDetailscholarship.text = dataScholar?.closeregistration
            tvBenefitDetailscholarship.text = dataScholar?.benefit
            tvRequirementsDetailscholarship.text = dataScholar?.requirement
            btnMoreInformation.setOnClickListener {
                information(dataScholar?.link.toString())
            }
        }
    }

    private fun setSavedState(isSaved: Boolean) {
        ib_saved_detailscholarship.setImageResource(if (isSaved) R.drawable.saved else R.drawable.ic_save)
    }

    fun information(informationLink : String){
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(informationLink)
        startActivity(intent)
    }
}