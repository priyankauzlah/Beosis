package com.uzlahalya.beosis4.activity

import ScholarshipItem
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Patterns
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.uzlahalya.beosis4.data.Scholarship
import com.uzlahalya.beosis4.databinding.ActivityDetailScholarshipBinding
import com.uzlahalya.beosis4.fragment.HomeFragment
import kotlinx.android.synthetic.main.activity_detail_most_popular.*
import kotlinx.android.synthetic.main.activity_detail_scholarship.*

class DetailScholarshipActivity : AppCompatActivity() {

    private lateinit var detailScholarshipBinding: ActivityDetailScholarshipBinding

    companion object{
        const val EXTRA_SCHOLARSHIP = "extra_scholarship"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailScholarshipBinding = ActivityDetailScholarshipBinding.inflate(layoutInflater)
        setContentView(detailScholarshipBinding.root)

        supportActionBar?.hide()

        ib_arrow_back_detail_scholarship.setOnClickListener {
           finish()
        }

        val dataScholar = intent.getParcelableExtra<ScholarshipItem>(EXTRA_SCHOLARSHIP)

        detailScholarshipBinding.apply {
            Glide.with(applicationContext).load(dataScholar?.image).into(ivImageDetailscholarship);
            tvTitleScholarshipDetailscholarship.text = dataScholar?.scholarshipName
            tvUniDetailscholarship.text = dataScholar?.universityName
            tvCountryDetailscholarship.text = dataScholar?.country
            tvMajorDetailscholarship.text = dataScholar?.major
            tvDegreeDetailScholarship.text = dataScholar?.degree
            tvDateOpenregistrationDetailscholarship.text = dataScholar?.openRegister
            tvDateDeadlineDetailscholarship.text = dataScholar?.closeRegister
            tvBenefitDetailscholarship.text = dataScholar?.benefits
            tvRequirementsDetailscholarship.text = dataScholar?.requirements
            btnMoreInformation.setOnClickListener {
                information(dataScholar?.link.toString())
            }
        }
    }

    fun information(informationLink : String){
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(informationLink)
        startActivity(intent)
    }
}