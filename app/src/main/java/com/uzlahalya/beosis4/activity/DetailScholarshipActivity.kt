package com.uzlahalya.beosis4.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.uzlahalya.beosis4.R
import com.uzlahalya.beosis4.databinding.ActivityDetailScholarshipBinding
import kotlinx.android.synthetic.main.activity_detail_scholarship.*

class DetailScholarshipActivity : AppCompatActivity() {

    lateinit var detailScholarshipBinding : ActivityDetailScholarshipBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailScholarshipBinding = ActivityDetailScholarshipBinding.inflate(layoutInflater)
        setContentView(detailScholarshipBinding.root)
        supportActionBar?.hide()
        ib_arrow_back_detailscholarship.setOnClickListener { startActivity(Intent(MainActivity.getLaunchService(this))) }

        getIntent().getStringExtra("SCHOLARSHIPLOGO")?.let { detailScholarshipBinding.ivImageDetailscholarship.setImageResource(it.toInt()) }
        detailScholarshipBinding.tvTitleScholarshipDetailscholarship.text = getIntent().getStringExtra("SCHOLARSHIPNAME")
        detailScholarshipBinding.tvUniDetailscholarship.text = getIntent().getStringExtra("SCHOLARSHIPUNIVERSITY")
        detailScholarshipBinding.tvCountryDetailscholarship.text = getIntent().getStringExtra("SCHOLARSHIPCOUNTRY")
        detailScholarshipBinding.tvMajorDetailscholarship.text = getIntent().getStringExtra("SCHOLARSHIPMAJOR")
        detailScholarshipBinding.tvDegreeDetailScholarship.text = getIntent().getStringExtra("SCHOLARSHIPDEGREE")
        detailScholarshipBinding.tvDateOpenregistrationDetailscholarship.text = getIntent().getStringExtra("SCHOLARSHIPOPENREGISTRATION")
        detailScholarshipBinding.tvDeadlineDetailscholarship.text = getIntent().getStringExtra("SCHOLARSHIPCLOSEREGRISTRATION")

    }
}